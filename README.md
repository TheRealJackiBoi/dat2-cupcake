# Cupcake Marts 2023
Det her er vores Cupcake webshops projekt fra 2. semester på datamatiker studiet på CPH Business Lyngby. Vi har bygget en webapplikation efter mvc strukturen. Den er bygget med en MySQL database, Java Backend

### Demovideo
https://www.youtube.com/watch?v=BQ2x0Y5cg0I

## Gruppemedlemmer
- Bjarke Olaf Dehlie - cph-bd89@cphbusiness.dk - https://github.com/artiecodes 
- William Emil Yus Hansen - cph-wh113@cphbusiness.dk - https://github.com/asphixia 
- Julius Peter Hvid Lassen - cph-jl442@cphbusiness.dk - https://github.com/Juliuslassen 
- Jack Thorsen Oulund - cph-jo190@cphbusiness.dk - https://github.com/TheRealJackiBoi

## Java version
I pom.xml er diverse dependencies valgt, så projektet kan bygges og køres i Java 8. Nyere versioner af 
Java vil formentlig også fungere, men vi har kun testet med version 8 og 11.

## Tomcat
Brug version 9.x



## Bemærkninger

### Startkoden indeholder følgende:

- Strukturering i passende packages for overblik (MVC). Noget af strukturen er også givet af Maven, og kan ikke laves om. F.eks. opdelingen i `/java` og `/webapp`.
- Javaservlets
- JSP sider. Læg dem i WEB-INF som kun skal tilgås via en servlet. Der ligger allerede `welcome.jsp`
- En super skrabet css-fil til styling
- Datamapper for user-tabellen, som anvender en connection pool. Den er package-protected
- En facadeklasse `UserFacade`, der bruges til at tilgå dine mappermetoder
- Fejlhåndtering med exceptions for databaseoperationer. Den skriver også til Tomcat log.
- Integrationstest af datamapperen for User.

### Funktionelt kan applikationen:

- Vise en forside med links til undersider, som endnu ikke er lavet
- Logge en user på. Der findes to brugere i databasen.
    1. `user` med password: `1234` (rolle: `user`)
    2. `admin` med password: `1234` (rolle: `admin`)
- Man kan se på `index.jsp` og `WEB-INF/welcome.jsp` hvordan man kan udnytte om en user er logget på eller ej.
- Hvis man indtaster ugyldige data under indlogning, bliver man sendt til en en fejlside.
- Logge en bruger af
- Metoden `isRoleAllowed(String role, HttpServletRequest request)` som ligger i pakken `services`. Den tjekker om en given bruger matcher en given rolle.

## MVC arkitektur

![](documentation/mvc.jpg)
