package me.kaward.spongechat.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author JonathanxD
 *
 */
final class ConfigurationSection {

	private static final String ident = "  ";
	private String sectionTransform;
	private List<String> parser = new ArrayList<String>();
	private static int TASK_FORM_SECTION = 0; //Formatted YAML to unformatted key/value map
	private static int TASK_SECTION_CHECK = 1; //Check sections exists
	private static int TASK_SECTION_TRANSFORM = 2; //Unformatted key/value map to formatted YAML
	private static int TASK_DIRECTIVE_CHECK = 3; //Check directive exists
	
	protected ConfigurationSection sectionTransform(String content) {
		HashMap<String, String> kv = new HashMap<>();// HashMap to Store keys and Values to send to function configureYAML
		for (int x = 0; x != content.split("\n").length; ++x) { //Split line-by-line
			String keyValue = content.split("\n")[x]; //Get current line
			String key = keyValue.split(":")[0]; //get the key
			String value = keyValue.split(":").length != 1 ? keyValue.split(":")[1] : ""; // get the value (if exists)
			
			kv.put(key, value); //Store key and value
		}
		sectionTransform = configureYAML(TASK_SECTION_TRANSFORM, kv);//Transform Keys and Values unformatted to formatted YAML.
		return this;
	}

	protected ConfigurationSection formSection() {	
		return configureYAML(TASK_FORM_SECTION); // Form the sections (update the sections)
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T configureYAML(int doTask, Object... args){
		String tmp = null; //For section unformatted to formatted YAML transform
		HashMap<String, String> map = null; //For section unformatted to formatted YAML transform
		if(doTask == TASK_FORM_SECTION || doTask == TASK_SECTION_CHECK || doTask == TASK_SECTION_TRANSFORM || doTask == TASK_DIRECTIVE_CHECK){
			
			if(doTask == TASK_FORM_SECTION)parser.clear(); // Clear parser (Configuration class)
			if(doTask == TASK_SECTION_TRANSFORM) tmp = ""; // Create clear (non-null) temporary string 
			if(doTask == TASK_SECTION_TRANSFORM) map = (HashMap<String, String>)args[0]; // Get unformatted key/value map from args
			
			String split[] = this.sectionTransform.split("\n"); // Split line by line of sectionTransform
			
			for(int x = 0; x < split.length; ++x){ // Loop lines
				
				String current = split[x]; // Get current line
				String next = x+1 != split.length ? split[x+1] : null; // Get next line
				
				HashMap<String, String> directiveToCreate = new HashMap<>(); // Map to store directives to create (safe process (prevent directive be inserted in current line, and be inserted in last line))
				
				if(doTask == TASK_SECTION_TRANSFORM){ // Value set fix
					Iterator<Map.Entry<String, String>> vs = map.entrySet().iterator(); // Make a iterator of map
					while(vs.hasNext()){ // Check if has next element
						Map.Entry<String, String> ms = vs.next(); // Get the element
						if(diretiveExists(ms.getKey()) == null && !ms.getKey().contains(".")){ // Check directive not exists												
							if(!directiveToCreate.containsKey(ms.getKey())){
								directiveToCreate.put(ms.getKey(), ms.getValue()); // Put to a create query
								vs.remove(); // Remove from map
							}
						}
					}
										
					if(!hasIdent(current) && !hasIdent(next) && current.split(":").length > 1){ // Check if is a directive
						String key = current.split(":")[0]; // Get directive key
						String value = removeIdents(current.split(":")[1]); // Get directive value
						if(map.containsKey(key)){ // Map contains key
							value = map.get(key); // Set value
							current = key+":"+value; // Set current;							
						}
					}
				}
				
				
				if(doTask == TASK_SECTION_TRANSFORM || doTask == TASK_DIRECTIVE_CHECK){ // Check directives and sections
					if((!hasIdent(current) && (next == null || !hasIdent(next)))){ // Check if element is directive
						if(doTask == TASK_DIRECTIVE_CHECK){ //Directives
							
							if(args[0].equals(current.split(":")[0])){ // Check if arg directive equal the current directive
								
								return (T) Boolean.TRUE; // Return TRUE Boolean Object
							}
						}						
						if(doTask == TASK_SECTION_TRANSFORM)tmp += current + "\n"; //Append directive to temporary variable
					}
				}
				
				int y = 0; // Variable to loop next elements (process)
				int z = 0; // Variable to loop next elements (store)

				HashMap<Integer, String> indexes = new HashMap<>(); //Map to put next elements (process - one-by-one)
				HashMap<Integer, String> indexies = new HashMap<>(); //Map to put next elements (store - all) 
				HashMap<String, String> sectionToCreate = new HashMap<>(); // Map to store sections to create (safe process (prevent section be inserted in current line, and be inserted in last line))
				
				ArrayList<String> xcurr = new ArrayList<String>(); // Key String Map
				
				if(!hasIdent(current) && current.split(":").length == 1){ // Check if is a section Main
					
					String main = null;
					if(getIdentsInt(current) == 0){
						main = current;
						tmp += main + "\n";
					}
					try{
						try{
							/**
							 * Lines store in xcurr var list and indexies var map.
							 */
							while(hasIdent(split[x+(++z)])){
								String nextCurr = split[x+z];
								indexies.put(getIdentsInt(nextCurr)+1, nextCurr);
								if(nextCurr.split(":").length != 1){
									String key = getHierarchy(indexies, nextCurr, main)+"."+nextCurr.replace(" ", "").split(":")[0];
									xcurr.add(key);
								}
								
							}
						}catch(Throwable t){}
						/**
						 * Process all elements and transform formatted YAML in keys: value
						 * Or process all elements and transform unformatted elements (sections) in formatted YAML 
						 */
						String nextCurr;
						while((nextCurr = split[x+(++y)]) != null){ // Go to next element
							//String nextCurr = split[x+y]; // Get next element
							
							/*if(removeIdents(nextCurr).startsWith("-")){ // Check if is List<?>
								System.out.println("List: "+nextCurr);
								while((nextCurr = split[x+(++y)]).startsWith("-")){
									
								}
								System.out.println("Jump: "+nextCurr);
								continue;
								
								// Is List
								// More complex process. Next task, DO IT.
							}*/
							if(hasIdent(nextCurr)){ //Is section element
								//Is section element
								indexes.put(getIdentsInt(nextCurr)+1, nextCurr); // Put to indexes map
							}	
							/*if(removeIdents(split[x+(y+1)]).startsWith("-")){
								System.out.println("List: "+nextCurr+" Task: "+doTask);
								String theFinal = "";
								theFinal += nextCurr + (doTask == TASK_SECTION_TRANSFORM ? "\n" : "");
								if(doTask == TASK_SECTION_TRANSFORM){
									tmp += theFinal;
								}
								String formingList = "-{[";
								while(removeIdents((nextCurr = split[x+(++y)])).startsWith("-")){
									if(doTask == TASK_SECTION_TRANSFORM){
										tmp += nextCurr + "\n";
										
									}else if(doTask == TASK_FORM_SECTION){
										formingList += removeIdents(nextCurr) + ", ";
									}

									
								}
								if(doTask == TASK_FORM_SECTION){
									if(formingList.length() > 3){
										formingList = getHierarchy(indexes, nextCurr, main)+"."+removeIdents(theFinal) + formingList.substring(0, formingList.length()-2);
									}
									formingList += "]}-";
									parser.add(formingList);
									tmp += formingList + "\n";
									
								}
								--y;
								System.out.println("Parser: "+parser);
								continue;
								
							}*/
							if(nextCurr.split(":").length != 1 || (nextCurr.split(":").length == 1 && removeIdents(split[x+(y+1)]).startsWith("-"))){//Check if is a section (= 1) or a directive (!= 1)								
								String value = nextCurr.split(":").length != 1 ? nextCurr.split(":")[1] : null; // Get value
								String key = hasIdent(nextCurr) ? getHierarchy(indexes, nextCurr, main)+"."+nextCurr.replace(" ", "").split(":")[0] : removeIdents(nextCurr).split(":")[0]; // Get key unformatted (like: One.Two.Three)																
								if(removeIdents(split[x+(y+1)]).startsWith("-")){
									String theFinal = "";
									theFinal += nextCurr + (doTask == TASK_SECTION_TRANSFORM ? "\n" : "");
									if(doTask == TASK_SECTION_TRANSFORM){
										tmp += theFinal;
									}
									String formingList = "-{[\n ";
									while(removeIdents((nextCurr = split[x+(++y)])).startsWith("-")){
										if(doTask == TASK_SECTION_TRANSFORM){
											tmp += nextCurr + "\n";
											
										}else if(doTask == TASK_FORM_SECTION){
											formingList += removeIdents(nextCurr) + "\n ";
										}

										
									}
									if(doTask == TASK_FORM_SECTION){
										if(!formingList.equals("-{[\n ")){
											formingList = key.substring(0, key.length()-1)+removeIdents(theFinal) + formingList.substring(0, formingList.length()-2);
										}
										formingList += "\n]}-";
										parser.add(formingList);
										tmp += formingList + "\n";
										
									}
									--y;
									continue;
									
								}
								if(removeIdents(key).startsWith("[") && removeIdents(key).endsWith("]")){ // Check if is Array[]
									// Is Array
									// Will be processed by Configuration class.
								}
								if(doTask == TASK_SECTION_TRANSFORM){ // Transform section (key/value map to formatted YAML)
									if(map.containsKey(key)){ // Check if map contains that key (exists)
										value = map.get(key); // Set process, old value (value) to new value (map.get)
										if(!isNull(value)){ // Check is not null value
											nextCurr = nextCurr.split(":")[0] + ": "+removeIdents(value); //Set value with no ident's.
										}
										map.remove(key); // Remove from map (prevent duplicate)
									} 
									if(!isNull(value)){ // Check is not null value
										if(hasIdent(nextCurr)){ //Is section element 
											indexes.put(getIdentsInt(nextCurr)+1, nextCurr); // Put to indexes to be a reference formatted YAML
											tmp += nextCurr +"\n"; // Add line to temporary var (document)
										}						
									}
								}
								if(hasIdent(nextCurr)){
									if(doTask == TASK_SECTION_CHECK){ // Check sections
										if(String.valueOf(args[0]).equals(key)){
											return (T)Boolean.TRUE; // Return TRUE Boolean object
										}
									}
									if(doTask == TASK_FORM_SECTION){ // Formatted YAML to key/value map (unformatted)
										if(!isNull(value)){
											parser.add(key+": "+value.trim()); // Parser are processed in Configuration class
										}									
									}
								}
							}else{
								if(doTask == TASK_SECTION_TRANSFORM){ // Process section create (HARD WORK :P (Know that, bugs aren't friends!))
									tmp += nextCurr + "\n"; // Append current to new line in temporary variable (document)
									Iterator<Map.Entry<String, String>> vs = map.entrySet().iterator(); // Make a iterator of map
									while(vs.hasNext()){ // Check if has next element
										Map.Entry<String, String> ms = vs.next(); // Get the element
										if((diretiveExists(ms.getKey()) != null && diretiveExists(ms.getKey())) || sectionExists(ms.getKey())){ // Check if directive or section exists
										}else{
											if(diretiveExists(ms.getKey()) != null && !diretiveExists(ms.getKey())){ // Check directive not exists
												//MARK: Removed! Moved to line: 61
											}else
											if(!sectionExists(ms.getKey())){ // Check if section not exists
												String key = getHierarchy(indexes, nextCurr, main)+"."+nextCurr.replace(" ", "").split(":")[0]; // Get key
												if(findSection(xcurr, ms.getKey())){ // Find the section, true if exists
													if(!findSectionEqual(xcurr, ms.getKey(), key)){ // Check equal current element (prevent bugs)
														continue;
													}

													if(ms.getKey().startsWith(key)){ // Check if equal current... ? Again? Ok
														String kay = getIdents(nextCurr)+ident+ms.getKey().split(key+"\\.")[1];//Set key to create
														String laast = null; // Last key
														for(int xx = 0; xx < kay.split("\\.").length; ++xx){ // Loop Elements (Like: One.Two.Three (0. One, 1. Two, 2. Three))
															String ks = kay.split("\\.")[xx]; // Get element key
															ks = removeIdents(ks); // Remove ident's
															ks = (laast == null ? getIdents(kay) : getIdents(laast)) + (xx != 0 ? ident : "")+ks; // Set element key idented
															if(xx == kay.split("\\.").length-1){ // Split element
																tmp += (ks += ":"+ms.getValue()) +"\n"; // Set key and value
															}else{
																tmp += ks + ":"+ "\n";// Set as section
															}
																	
																	
															laast = ks;														
															
														}
													}
												}else{
													sectionToCreate.put(ms.getKey(), ms.getValue());
												}
												vs.remove();
											}
										}
									}
								}
							}
						}
						
					}catch(ArrayIndexOutOfBoundsException exception){
					}		
					x = x+(y-1);
				}
				if(doTask == TASK_SECTION_TRANSFORM){
					Iterator<Map.Entry<String, String>> vs = directiveToCreate.entrySet().iterator();
					while(vs.hasNext()){
						Map.Entry<String, String> curr = vs.next();
						tmp += curr.getKey()+":"+curr.getValue() +"\n";
					}
					Iterator<Map.Entry<String, String>> ds = sectionToCreate.entrySet().iterator();
					while(ds.hasNext()){
						Map.Entry<String, String> curr = ds.next();
						String kay = curr.getKey();
						String laast = null;
						for(int xx = 0; xx < kay.split("\\.").length; ++xx){
							String ks = kay.split("\\.")[xx];
							ks = removeIdents(ks);
							ks = (laast == null ? getIdents(kay) : getIdents(laast)) + (xx != 0 ? ident : "")+ks;
							if(xx == kay.split("\\.").length-1){
								tmp += (ks += ":"+curr.getValue()) +"\n";
							}else{
								tmp += ks + ":"+ "\n";	
							}
									
							laast = ks;														
						}
					}
				}
				
				if(doTask == TASK_FORM_SECTION) if((!hasIdent(current) && (next == null || !hasIdent(next)))){
					parser.add(current);
				}
			}
			if(doTask == TASK_FORM_SECTION) return (T) this;
			if(doTask == TASK_SECTION_CHECK /*|| doTask == TASK_DIRETIVE_CHECK*/) return (T) Boolean.FALSE;
			if(doTask == TASK_SECTION_TRANSFORM) return (T) tmp;
		}
		return null;
		
	}	
	
	private boolean findSection(ArrayList<String> sArray, String find){
		for(String s : sArray){
			s = s.substring(0, s.lastIndexOf("."));
			if(find.contains(s)){
				return true;
			}
		}
		return false;
	}

	private boolean findSectionEqual(ArrayList<String> sArray, String find, String curr){
		for(String s : sArray){
			s = s.substring(0, s.lastIndexOf("."));
			String similar = moreSimilar(sArray, find);
			if(similar.endsWith(".")) similar = similar.substring(0, similar.length()-1);
			if(find.contains(s) && curr.equals(similar)){
				return true;
			}
		}
		return false;
	}
	
	private String moreSimilar(List<String> list, String similarCheck){
		String c = list.get(0);
		TreeMap<Integer, String> points = new TreeMap<Integer, String>();		
		for(int x = 0; x < list.size(); ++x){
			c = list.get(x);
			for(int y = similarCheck.length(); y != 0; --y){
				for(int z = c.length(); z != 0; --z){
					if(c.substring(0, z).equals(similarCheck.substring(0, y))){
						points.put(y, c.substring(0, z));
					}
				}
			}
		}
		return points.lastEntry().getValue();
	}
	
	private boolean isNull(String value) {
		value = removeIdents(value);
		return value == null || value.equalsIgnoreCase("NULL");
	}

	private String getHierarchy(HashMap<Integer, String> hierarchy, String element, String main){
		String result = "";
		while(hierarchy.get(getIdentsInt(element)) != null){
			result += hierarchy.get(getIdentsInt(element)); 
			element = hierarchy.get(getIdentsInt(element));
		}
		result += main;
		result = result.replace(" ", "");
		result = result.replace(":", ".");
		String finalResult = "";
		for(int x = result.split("\\.").length-1; x != -1; --x){
			finalResult += result.split("\\.")[x]+".";
		}
		return finalResult.substring(0, finalResult.length()-1);
		
	}

	private boolean sectionExists(String section){
		Object result = configureYAML(TASK_SECTION_CHECK, section);
		return (result != null && (Boolean)result == Boolean.TRUE  ? true : false);
	}
	

	private Boolean diretiveExists(String diretive){
		Object result = configureYAML(TASK_DIRECTIVE_CHECK, diretive);
		if(result == null)return null;
		return (result != null && (Boolean)result == Boolean.TRUE  ? true : false);
	}
	
	protected void setNewSectionTransform(String transform) {
		this.sectionTransform = transform;
	}

	protected void setNewSectionTransform(List<String> transform) {
		this.sectionTransform = "";
		for(String s : transform){
			this.sectionTransform += s+"\n";
		}
		
	}

	protected String getResultOfTransform() {
		return this.sectionTransform;
	}

	private String getIdents(String s) {
		if(s == null) return "";
		int local = 0;
		for (int x = 0; x < s.length(); ++x) {
			if (s.charAt(x) == ' ') {
				++local;

			}
		}
		return s.substring(0, local);
	}

	private int getIdentsInt(String s) {
		if(s == null) return 0;
		int local = 0;
		for (int x = 0; x < s.length(); ++x) {
			if (s.charAt(x) == ' ') {
				++local;

			}
		}
		return (local > 1 ? local / 2 : local <= 0 ? 0 : 1);
	}

	private String removeIdents(String s){
		if(s == null) return s;
		int local = 0;
		for (int x = 0; x < s.length(); ++x) {
			if (s.charAt(x) == ' ') {
				++local;
			}else{
				break;
			}
		}		
		return s.substring(local);
	}
	
	private boolean hasIdent(String s) {
		if(s == null) return false;
		return s.startsWith(ident);
	}

	public List<String> formResult() {
		return parser;
	}

}
