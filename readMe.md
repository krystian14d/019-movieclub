####Latest branch: 
search-feature

####Run app using: 
pl.javastart.movieclub.MovieclubApplication

#MovieClub Application

Aim of this project is to write kind of website, like for example FilmWeb. 
Project was initially developed during Spring course on JavaStart learning platform, w
here very basic functionalities (FrontEnd + Backend) were implemented.

####Technological stack:
 - Java,
 - Spring Security, Boot, JPA, MVC,
 - MySQL database
 - Liquibase - DB version control,
 - Lombok,
 - JUnit 5,
 - AssertJ
 - Mockito

####Functionalities implemented during Spring course on JavaStart platform:
 - user registration and user roles,
 - possibility to add movies,
 - possibility to rate the movies

####Functionalities implemented by myself (both Back-end and Front-end work with corresponding html sites):
- unit testing - in progress (extending coverage during new features implementation)
- possibility to edit movie,
- possibility to edit genre,
- possibility to remove movie,
- possibility to remove the genre,
- added possibility to comment the movie by registered users,
- added pagination to comments and movie display,
- added GlobalExceptionHandler - @ControllerAdvice,
- comment administration (edit/remove) by users with editor or admin role,
- possibility to search movie,
- public user profile with possibility to see user comments and ratings.
- possibility to report errors in movie content - ONGOING

####Further plans for features to be implemented:
 - possibility to administrate users with ability to ban users,
 - possibility to edit user profile (change email or password),
 - possibility to add movie by every registered user, and then movie shall be accepted by user with editor or admin role