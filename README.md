![Header](./github-header-image.png)
# Shawn's GitHub Page

Welcome to my ePortfolio! This protfolio outlines three areas of focus including software design and engineering, algoorithm and data structures, and databases. I've also included a code review of an application before enhancements were performed.

## About Me
I started my computer science journey during my jounior year of high school 20 years ago. After high school, I attended a local community college where I completed an associates in Business and Information Technology degree. I then went on to start a degree in computer science but never finished. Last year, I decide to finish up my degree and decided on Southern New Hamshire University. This term will wrap up my computer science courses at SNHU and next term will wrap the rest of my courses. I'll have a few transfer courses to finish and transfer over. 

I've also been working as a full stack developer for a credit union over the last eight years. During that time, I've worked on .NET, Java, Angular, and SQL projects. 

One of the applications I've worked on include a rewards program. I helped design and implement the underlying SQL database. We also had to design and admin portal and integrate the application into various banking systems. I've also worked with Jenkins to design and implement automation pipelines. These pipelines include automated testing, building, and deploying to various environments. Mentoring is something that's important to me and I've had opportunties to help train new developers joining the team.

## Code Review

My code review was done on my Android inventory app. As part of my review, I pointed out screens that had didn't function as intended. The list of inventory didn't refresh after an item was delete. Inventory also wasn't tied to a specific user meaning anyone logged in coul see the inventory.

Security recommendations regarding enrollment and login were also discussed. Enrollment allowed for a single character password which doesn't provide significant security. Secrity could be improved by setting password requirements. MFA would also improve security.

The last part I review was styling and comments. The styling was consistent and followed common Java practices. Comments were light though and missed blocks of comments documenting processes.

Link: [Code Review](https://github.com/sjcurtis/sjcurtis.github.io/blob/main/Code%20Review%20Compressed.mp4)



## Software Design and Engineering

This artifact is a rewrite of an inventory application for Android rewritten in .NET. I selected this application because it covers a lot of the course outcomes. The biggest areas are software engineering techniques and communication. This artifact also demonstrates some security aspects. The artifact demonstrates database design and integration, and I used Entity Framework to accomplish this. Entity Framework is also a popular method to integrate with MVC and will look good in a portfolio. Using MVC helps separate logic from models and views and pushes it into the controller.

### Narrative

The goal of this project was to convert my Android inventory app to C# .NET. The original artifact was written in Android using Java with a SQL database. The app consisted of a login page where users could enroll or log in using the form. Logged-in users could create, edit, and delete inventory from a list. Users could also update quantities. My first step was to look at parts of the application that could be reused. The SQL database came close. Unfortunately, the database was built into the project. Ideally, if the database was hosted externally from the project, then I could just write an API to make calls to the database. Anyway, this ended up needing to be created from scratch.

I started with a new .NET project with HTTPS enabled for best security practices. This would encrypt traffic for the user. The next step was to create a SQL database. I used an Entity Framework code first approach. The approach has the user create models first. The models are then used to script the creation of the database based on the models. After the migrations were run, I could look at the database to verify the structure is what I was expecting. It took a few migrations to get the scheme correct. 

The next step was to create controllers and start pulling data. I added an inventory controller and used breakpoints and watch windows to verify the data coming back from the database. I then created a view with a table to list items in the table. It was then easy to expand the CRUD operations.

The last step was to add login and enrollment functionality. I added a separate controller for enrollment so the logic could be easily expanded in the future.


### Course Outcomes

I met the course objectives that I planned out. The readme demonstrates the ability to communicate project information to other developers. Security is demonstrated by requiring users to register and login. Inventory is also tied to the user who created it. Using MVC and Entity Framework are modern approaches to develop web-based solutions. MVC allows logic to be separated and can be used in larger teams. 

.NET isn’t new to me, but I am a bit rusty. The biggest challenge, I usually face, is setting up Entity Framework using a code first approach. It takes time to get the models and code setup to be able to start doing database migrations. Once everything is linked together and the first database migration works then it’s easy to start adding enhancements.

Original Artifact: [Android Inventory App](./AndroidInventoryApp)

Updated Artifact: [.NET Inventory App](./DotNetInventoryApp)


## Algorithm and Data Structures

Original Artifact: [Android Inventory App](./AndroidInventoryApp)

Updated Artifact: [Android Inventory App](./InventoryProjectSecurityUpdate)


## Database

Original Artifact: [Animal Shelter Juypiter Notebook](./JuypiterNotebook)

Updated Artifact: [Android Inventory App](./InventoryProjectSecurityUpdate)

## References
- Generate Header Images: https://leviarista.github.io/github-profile-header-generator/
- MP4 Compressor: https://www.veed.io/edit/78bf2d2a-e9c2-406b-adc5-038084c83bd2/compress?source=%2Ftools%2Fvideo-compressor%2Fmp4-compressor
 