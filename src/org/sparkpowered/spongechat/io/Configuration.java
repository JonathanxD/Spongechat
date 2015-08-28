package org.sparkpowered.spongechat.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class Configuration implements Serializable, Cloneable
{

	private static final long serialVersionUID = -5373342148735836183L;
	private List<String> parser = new ArrayList<String>();
	private HashMap<String, Object> builder = new HashMap<String, Object>();
	private File file = null;
	private static final String desktop = System.getProperty("user.home")+(System.getProperty("os.name").toLowerCase().contains("win") ? File.separator+"Desktop" : "");
	private final ConfigurationSection cs = new ConfigurationSection();;
	
	public static void main(String[] args) throws IOException
	{
		File file = new File(desktop, "tests.yml");
		Configuration c = new Configuration(file).parse();

		Logger log = Logger.getLogger("Configuration");
		log.info("Digite uma key para verificar o parser:");

		Scanner s = new Scanner(System.in);
		String key = s.nextLine();
		log.info("Resultado [" + key + ": " + c.builder.get(key) + "]\n");		

		log.info("Agora, digite algo para MUDAR:");
		String keychange = s.nextLine();

		log.info("Entendi.. Digite o novo valor:");
		String valuechange = s.nextLine();
		if(valuechange.isEmpty()){
			valuechange = null;
		}
		c.set(keychange, valuechange);

		log.info("Digite algo para salvar o arquivo.");
		s.nextLine();

		s.close();
		c.save();
		c.parse();
	}

	public Configuration(File file)
	{
		this.file = file;
	}

	public <Value> Value section(String key)
	{
		return null;
	}

	public Configuration parse() throws IOException
	{
		this.builder.clear();
		this.parser.clear();

		FileInputStream input = new FileInputStream(file);
		InputStreamReader stream = new InputStreamReader(input, Charset.forName("UTF-8"));
		BufferedReader reader = new BufferedReader(stream);

		String str = "";
		while ((str = reader.readLine()) != null)
		{
			parser.add(str);
		}

		reader.close();

		@SuppressWarnings("unused")
		Logger log = Logger.getLogger("Configuration");
		
		cs.setNewSectionTransform(parser);
		parser = cs.formSection().formResult();
		for (String parser0 : parser)
		{			
			
			
			if (!parser0.startsWith("#") && !parser0.isEmpty())
			{
				String k = parser0.split(":")[0];
				String v = parser0.split(k)[1];
	
				if (v.startsWith(" "))
				{
					v = v.replaceFirst(" ", "");
				}

				if (v.startsWith(": "))
				{
					v = v.replaceFirst(": ", "");
				}

				if (v.startsWith(" : "))
				{
					v = v.replaceFirst(" : ", "");
				}
				builder.put(k, v);
			}
		}
		

		return this;
	}

	public void save() throws IOException
	{
		Logger log = Logger.getLogger("Configuration");
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter os = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
		Set<String> preventive = new HashSet<String>();
		BufferedWriter writer = new BufferedWriter(os);
		String content = "";

		for (String map : parser)
		{
			log.info("parsing: " + map);
			if (map.startsWith("#"))
			{
				content = map + "\n";
			}
			else
			{
				String k = map.split(":")[0];
				log.info("---> key = " + k);
				
				String v = map.split(k + ":").length == 1 ? v = "" : map.split(k + ":")[1]; //Fix For Sections

				if (v.startsWith(" "))
				{
					v = v.replaceFirst(" ", "");
				}

				if (v.startsWith(": "))
				{
					v = v.replaceFirst(": ", "");
				}

				if (v.startsWith(" : "))
				{
					v = v.replaceFirst(" : ", "");
				}

				if (builder.containsKey(k))
				{
					if (!preventive.contains((k + ": " + v)))
					{
						content += (k + ": " + v) + "\n";
						preventive.add((k + ": " + v));
					}
				}
			}
		}

		content = cs.sectionTransform(content).getResultOfTransform(); //By Jonathan
		writer.write(content);
		writer.flush();
		writer.close();
	}

	
	
	public void set(String key, Object value)
	{
		builder.put(key, value);

		for (int i = 0; i < parser.size(); i++)
		{
			if (parser.get(i).startsWith(key + ":"))
			{
				parser.set(i, key + ": " + value);
			}
		}

		parser.add(key + ": " + value);
	}

	public String getString(String key)
	{
		return String.valueOf(builder.get(key));
	}

	public Integer getInt(String key)
	{
		return Integer.parseInt(String.valueOf(builder.get(key)));
	}

	public Boolean getBoolean(String key)
	{
		return Boolean.parseBoolean(String.valueOf(builder.get(key)));
	}

	public Float getFloat(String key)
	{
		return Float.parseFloat(String.valueOf(builder.get(key)));
	}
		

}
