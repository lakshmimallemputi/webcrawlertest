WebCrawler:

Given URL to a web page , WebCrawler class go to that webpage and collect all urls on the page.
It will go to next page and repeat. In the process it starts putting all urls it found into sitemap file.

Webcrawler produces output file by crawling the hrefs from  http://wiprodigital.com.
If the output is alredy exists then the file will be deleted and outputfile will be created with all the

references of that url.The output file contains a simple structured site map, showing links to other pages under the same domain.

Inorder to run this application need following dependenices. These have been added to pom dependcies.
1.jsoup
2. maven exec plugin.


Follwoing are ways to build and run this application.

1.mvn clean test

After running project the output file will be created in the Project folder.
Output can be found in sitemap.txt

2.Run WebCrawler class main method using any IDE


Excluded URLS:
Linkdin
Google
Twitter
Facebook
and any urls not based on http or https protocol




What improvementscould be done if more time provided:

If Time been provided I could have worked on the logic exlusion of invliad urls with regular expressions instead of hardcoded values.
Could have written application runs with multithreading for better execution speed.




