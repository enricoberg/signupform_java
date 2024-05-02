  
  


<a  name="readme-top"  id="readme-top"></a> 

  

<h3  align="center">SIGN UP FORM</h3>

  

  

<!-- ABOUT THE PROJECT -->
## About The Project 

The application consists of a basic form for login / signup with back-end validation of fields at signup.
The app has a table to visualize the data of all registered users (accessible after authentication).
The graphic interface allows modification of any field of the user table on the database, as well as user deletion exploiting the API for CRUD operations.
The authentication system is based on user sessions, that are stored in the database.
The users and sessions informations are archived in a MySQL database.


### Built With

  
![HTML LOGO](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=FFF)

![CSS LOGO](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3)

![BOOTSTRAP LOGO](https://img.shields.io/badge/BOOTSTRAP-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=FFF)


![JAVASCRIPT LOGO](https://img.shields.io/badge/Java-Script-F7DF1E?style=for-the-badge&logo=Javascript)

![MySQL](https://img.shields.io/badge/mysql-000000?style=for-the-badge&logo=mysql&logoColor=white)

  

![100% ](https://img.shields.io/badge/-JAVA-violet?style=for-the-badge)

  
  ![SPRING BOOT LOGO](https://img.shields.io/badge/Spring-BOOT-6DB33F?style=for-the-badge&logo=Spring-Boot&logoColor=FFF)
  
  
  
  
  
  

  
  
  

  

<!-- GETTING STARTED -->

  

## Getting Started

  

  

  
  

  

### Installation

First you need to have Java installed, for this app you will need [JDK 17].(https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)


You will also need to create a MySQL database and have a software installed to host it locally.
I personally use XAMPP, that you can  [download  here.](https://www.apachefriends.org/download)
Once you have it installed, you can run the application by clicking START on the Apache and MySQL modules.

  ![XAMPP SCREEN](https://i.postimg.cc/zfDb5csn/xampp.png)


I have created a sql file in the main project folder that you can copy-paste to create the database (database_setup.sql).
Then you need to set up the right properties of the app.
Inside the folder you will find  the application.properties file.
Open this file and insert the right credentials to access your database (url, username, password).
You can execute the pre-built JAR file [(download it here)](https://kdrive.infomaniak.com/app/share/965911/07ae7f0f-5fe9-433a-9872-369080049b5f) directly from CMD:


```xml

java -jar Signupform.jar

```

  

<!-- USAGE EXAMPLES -->

You're good to go! You can now access the app on the browser from [this URL](http://localhost:8080/app/).


## Edit the Project
If you want to make modifications to the app, you are free to do it by downloading the source code.


### Building
##### Build with IDE
To compile and run the application I personally use an IDE for simplicity, specifically  [IntelliJ IDEA.](https://www.jetbrains.com/idea/download/?section=windows)
To import a Maven project from IntelliJ you can follow [this detailed guide.](https://www.jetbrains.com/guide/java/tutorials/working-with-maven/importing-a-project/)
To create the JAR file from within the project,
```xml
File > Project Structure>Artifacts > Add a new one (+)
```
```xml
JAR > From modules with dependencies
```
```xml
Select the Main class as entry point for the app and Apply
```
Now go to 
```xml
File > Build > Build Artifacts... > Build
```
And the JAR file will be exported in the selected folder. 🎉
Now to launch the app, open a terminal within the Jar folder and type:
```xml
java -jar YOURJARNAME.jar
```
Note: change to your actual jar file name.
#### Build from command line
If you want to build from command line, first make sure you have [Maven installeed.](https://maven.apache.org/install.html)
Now open the the folder of the project with the terminal.
Type:
```xml
mvn package
```

The JAR file will be generated inside the **target/** folder.
You can change the destination by changing the filename and directory inside the build tag in the pom.xml
```xml
<build>

<directory>out</directory>

<finalName>Your-app-name</finalName>

</build>
```
Now simply open a terminal and run:
```xml
java -jar YOURJARNAME.jar
```



## Contributing

  

  

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

  

  

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".

  

Don't forget to give the project a star! Thanks again!

  

  

1. Fork the Project

  

2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)

  

3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)

  

4. Push to the Branch (`git push origin feature/AmazingFeature`)

  

5. Open a Pull Request

  

  
  
  

  
  
  

  

<!-- CONTACT -->

  

## Contact

  

  

Enrico Bergamini - enricobergamini@outlook.it

  

[![LinkedIn][linkedin-shield]][linkedin-url]

  

  

<p  align="right">(<a  href="#readme-top">back to top</a>)</p>

  
  

  
  

<!-- MARKDOWN LINKS & IMAGES -->

  

<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

  

[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge

  

[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors

  

[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge

  

[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members

  

[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge

  

[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers

  

[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge

[HTML-url]: https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=whit

[issues-url]: https://github.com/othneildrew/Best-README-Template/issues

  

[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge

  

[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt

  

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

  

[linkedin-url]: https://linkedin.com/in/enrico-bergamini

  

[product-screenshot]: images/screenshot.png

  

[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white

  

[Next-url]: https://nextjs.org/

  

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB

  

[React-url]: https://reactjs.org/

  

[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D

  

[Vue-url]: https://vuejs.org/

  

[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white

  

[Angular-url]: https://angular.io/

  

[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00

  

[Svelte-url]: https://svelte.dev/

  

[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white

  

[Laravel-url]: https://laravel.com

  

[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white

  

[Bootstrap-url]: https://getbootstrap.com

  

[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white

  

[JQuery-url]: https://jquery.com

[CSS-url]: https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=whit

[JAVASCRIPT-url]: https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black