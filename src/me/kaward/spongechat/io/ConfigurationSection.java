package me.kaward.spongechat.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class ConfigurationSection {

	private static final String ident = "  ";
	private String sectionTransform;
	private List<String> parser = new ArrayList<String>();
	private static int TASK_FORM_SECTION = 0;
	private static int TASK_SECTION_CHECK = 1;
	private static int TASK_SECTION_TRANSFORM = 2;
	private static int TASK_DIRETIVE_CHECK = 3;
	
	protected ConfigurationSection sectionTransform(String content) {
		HashMap<String, String> kv = new HashMap<>();
		for (int x = 0; x != content.split("\n").length; ++x) {
			String keyValue = content.split("\n")[x];
			String key = keyValue.split(":")[0];
			String value = keyValue.split(":").length != 1 ? keyValue.split(":")[1] : "";
			
			kv.put(key, value);
		}
		sectionTransform = configureYAML(TASK_SECTION_TRANSFORM, kv);
		return this;
	}

	protected ConfigurationSection formSection() {	
		return configureYAML(TASK_FORM_SECTION);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T configureYAML(int doTask, Object... args){
		String tmp = null;
		HashMap<String, String> map = null;
		if(doTask == TASK_FORM_SECTION || doTask == TASK_SECTION_CHECK || doTask == TASK_SECTION_TRANSFORM || doTask == TASK_DIRETIVE_CHECK){
			if(doTask == TASK_FORM_SECTION)parser.clear();
			if(doTask == TASK_SECTION_TRANSFORM) tmp = "";
			if(doTask == TASK_SECTION_TRANSFORM)map = (HashMap<String, String>)args[0];
			String split[] = this.sectionTransform.split("\n");
			for(int x = 0; x < split.length; ++x){
				String current = split[x];
				String last = x > 0 ? split[x] : null;
				String next = x+1 != split.length ? split[x+1] : null;
				int y = 0;
				HashMap<Integer, String> indexes = new HashMap<>();
				if(doTask == TASK_SECTION_TRANSFORM || doTask == TASK_DIRETIVE_CHECK){
					if((!hasIdent(current) && (next == null || !hasIdent(next)))){
						if(doTask == TASK_DIRETIVE_CHECK){
							
							if(args[0].equals(current.split(":")[0])){
								
								return (T) Boolean.TRUE;
							}
						}						
						if(doTask == TASK_SECTION_TRANSFORM)tmp += current + "\n";
					}
				}
				HashMap<String, String> directiveToCreate = new HashMap<>();
				if(!hasIdent(current) && current.split(":").length == 1){
					
					String main = null;
					if(getIdentsInt(last) == 0){
						main = last;
						tmp += main + "\n";
					}
					try{
						while(hasIdent(split[x+(++y)])){
							String nextCurr = split[x+y];
							indexes.put(getIdentsInt(nextCurr)+1, nextCurr);
							if(nextCurr.split(":").length != 1){
								String value = nextCurr.split(":")[1];
								String key = getHierarchy(indexes, nextCurr, main)+"."+nextCurr.replace(" ", "").split(":")[0];
								if(doTask == TASK_SECTION_TRANSFORM){								
									if(map.containsKey(key)){
										value = map.get(key);
										if(!isNull(value)){
											nextCurr = nextCurr.split(":")[0] + ": "+removeIdents(value);
										}
										map.remove(key);
									} 
									if(!isNull(value)){
										indexes.put(getIdentsInt(nextCurr)+1, nextCurr);
										tmp += nextCurr +"\n";
									}									
								}
								if(doTask == TASK_SECTION_CHECK){
									if(String.valueOf(args[0]).equals(key)){
										return (T)Boolean.TRUE;
									}
								}
								if(doTask == TASK_FORM_SECTION){
									if(!isNull(value)){
										parser.add(key+": "+value.trim());
									}
									
								}
							}else{
								if(doTask == TASK_SECTION_TRANSFORM){
									tmp += nextCurr + "\n";
									Iterator<Map.Entry<String, String>> vs = map.entrySet().iterator();
									while(vs.hasNext()){
										Map.Entry<String, String> ms = vs.next();
										if(sectionExists(ms.getKey()) || diretiveExists(ms.getKey())){
											//TODO
										}else{
											System.out.println("Diretive: "+ms.getKey()+" "+diretiveExists(ms.getKey()));
											if(!diretiveExists(ms.getKey())){
												directiveToCreate.put(ms.getKey(), ms.getValue());
												vs.remove();
											}else
											if(!sectionExists(ms.getKey())){
												String key = getHierarchy(indexes, nextCurr, main)+"."+nextCurr.replace(" ", "").split(":")[0];
												if(ms.getKey().startsWith(key)){
													if(sectionExists(ms.getKey()) || diretiveExists(ms.getKey())){
														throw new UnsupportedOperationException("Cannot set NEW value to a existing section/diretive, try set it to null");
													}
													String kay = ident+ms.getKey().split(key+"\\.")[1];
													
													String laast = null;
													for(int xx = 0; xx < kay.split("\\.").length; ++xx){
														String ks = kay.split("\\.")[xx];
														
														ks = (laast == null ? getIdents(kay) : getIdents(laast)) + (xx != 0 ? ident : "")+ks;
														if(xx == kay.split("\\.").length-1){
															tmp += (ks += ":"+ms.getValue()) +"\n";
														}else{
															tmp += ks + ":"+ "\n";	
														}
																
																
														laast = ks;														
														
													}
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
					if(doTask == TASK_SECTION_TRANSFORM){
						Iterator<Map.Entry<String, String>> vs = directiveToCreate.entrySet().iterator();
						while(vs.hasNext()){
							Map.Entry<String, String> curr = vs.next();
							tmp += curr.getKey()+":"+curr.getValue() +"\n";
						}
					}
				}
				
				if(doTask == TASK_FORM_SECTION) if((!hasIdent(current) && (next == null || !hasIdent(next)) )){
					parser.add(current);
				}
			}
			if(doTask == TASK_FORM_SECTION) return (T) this;
			if(doTask == TASK_SECTION_CHECK || doTask == TASK_DIRETIVE_CHECK) return (T) Boolean.FALSE;
			if(doTask == TASK_SECTION_TRANSFORM) return (T) tmp;
		}
		return null;
		
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
	

	private boolean diretiveExists(String diretive){
		Object result = configureYAML(TASK_DIRETIVE_CHECK, diretive);
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

	protected void updateSentence(String sentence) {
		;
	}

	protected void setBuilder(HashMap<String, Object> builder) {
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
