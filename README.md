# ConnectTheDots (Java / Spring Edition)

ConnectTheDots is a functional LinkedIn clone that allows a user to build a professional network by creating an account and connecting with other users on the platform. The user can create, edit, and delete their posts, as well as invite other users to connect, and accept/reject any invitations they receive. Users also have the option to attach photos to posts or to their user show page as a profile picture.

This application was first implemented with a [Ruby / Rails backend](https://github.com/grayson-poon/ConnectTheDots), but now that I'm trying to learn Java / Spring, I figured I'd re-implement this project's REST APIs. 

Live Link: TBD :)

## Local Setup

Technologies:

* Java 17.0.14
* MySQL 8.0.41
* Gradle 8.13

are what I use, and it's recommended that you have the same versions installed to run this project locally.

1. Once you have MySQL 8.0 installed, create a local MySQL user called `client_primary` with no password (this way it's easier for you to use this user on your machine, without needing to input a password every time).
