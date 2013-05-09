import java.io.*;

public class ExtencionFilesStuff {

	static public void main(String arg[]) throws java.io.IOException {
		String input="C:/Users/nilda/Desktop/PageDev/icom5016_g18_sourceCode/", outputPath="";
		boolean verbose =true;
		if(arg.length>0){
			input = arg[0];
			if(arg.length>1&&arg[1].equalsIgnoreCase(" -v ")){
				verbose = true;
			}
		}
		PrintWriter pw=null;
		File file = new File(input);
		File[] files;
		java.util.List<File[]> folders = new java.util.LinkedList<File[]>();
		folders.add(file.listFiles());
		
		while(!folders.isEmpty()){
			files=folders.remove(0);
			for (int i = 0; i < files.length; i++) {
				if(verbose)
					System.out.println("Processing " + files[i].getPath() + "... ");
				
				if(files[i].getName().contains("~")||files[i].getName().contains("zip"))
					continue;
				
				/* Eliminate non file listed and windows temporary files*/
				if(!(files[i].isFile())){
					//here goes recursive definition
					folders.add(files[i].listFiles());
					continue;
				}
				BufferedReader br = new BufferedReader(new FileReader(files[i]
						.getPath()));
				outputPath=files[i].getPath();
				if(outputPath.contains(".noextencion")){
					outputPath=outputPath.substring(0,outputPath.indexOf(".noextencion"));
				}
				if(verbose)
					System.out.println("Processing " + outputPath + "... ");

				pw = new PrintWriter(new FileOutputStream(outputPath));
				String line = br.readLine();

				while (line != null) {
					pw.println(line);
					line = br.readLine();
				}
				br.close();
				pw.close();
			}
		}

		System.out.println("Done. ");
	}
}