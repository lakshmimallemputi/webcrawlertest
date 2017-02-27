

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileWriter;

import java.io.IOException;

import java.io.InputStreamReader;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;





/*This Program is a crawler Program which crawls given a starting URL – say http://wiprodigital.com

This Program visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.

The output file contains a simple structured site map, showing links to other pages under the same domain,

links to external URLs and links to static content such as images for each respective page.

Read the URLs and their domains, considers valid URLS and rejects the invalid urls.

Writes the URLS into output file(sitemap.txt) into the Project location where the Project exists.

The program makes the initial validation to check the file existance.If the file already exists then file will be deleted.*/



public class WebCrawler {



    public static void main(String[] args) throws IOException {



        File dir = new File(".");

        String loc = dir.getCanonicalPath() + File.separator + "sitemap.txt";

        File fileToDelete = new File(loc);

        if(fileToDelete.delete()) {

            System.out.println(fileToDelete.getName() + " is deleted!");

        }

        FileWriter fstream = new FileWriter(loc, true);

        BufferedWriter out = new BufferedWriter(fstream);

        out.newLine();

        out.close();




        System.out.println("Inprogress...");
        processPage("http://wiprodigital.com");



        File file = new File(loc);





    }



    /*Given url and a File

      return true if the url is contained in the File already otherwise false

    */

    public static boolean checkExist(String url, File fin) throws IOException {



        FileInputStream fis = new FileInputStream(fin);

        // //Construct the BufferedReader object

        BufferedReader in = new BufferedReader(new InputStreamReader(fis));



        String aLine = null;

        while ((aLine = in.readLine()) != null) {

            // //Process each line

            if (aLine.trim().contains(url)) {

                in.close();

                fis.close();

                return true;

            }

        }

        //close the buffer reader

        in.close();

        fis.close();



        return false;

    }



    public static void processPage(String URL) throws IOException {



        File dir = new File(".");

        String loc = dir.getCanonicalPath() + File.separator + "sitemap.txt";



        // skip the invalid URLS or other sites

        if (URL.contains("http://www.linkedin.com")

                || URL.contains("https://www.facebook.com")

                || URL.contains("http://twitter.com")

                || URL.contains("https://www.google.co.uk")

                || !URL.startsWith("http"))

            return;



        // process the url first

        if (URL.contains("wiprodigital.com") && !URL.endsWith("/")) {



        } else if(URL.contains("wiprodigital.com") && URL.endsWith("/")){

            URL = URL.substring(0, URL.length()-1);

        }else{

            // url of other site -> do nothing

            return;

        }



        File file = new File(loc);



        // check existance of url in file

        boolean e = checkExist(URL, file);

        if (!e) {

            // insert to file

            FileWriter fstream = new FileWriter(loc, true);

            BufferedWriter out = new BufferedWriter(fstream);

            out.write(URL);

            out.newLine();

            out.close();

            Document doc = null;

            try {

                doc = Jsoup.connect(URL).get();

            } catch (IOException e1) {

                e1.printStackTrace();

                return;

            }



            Elements urllist = doc.select("a[href]");

            for (Element link : urllist) {



                processPage(link.attr("abs:href"));



            }



        } else {

            // do nothing

            return;

        }



    }

}

