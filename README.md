# Github-Cv-Converter
# How to use the program:

_**_1-Step 1: create a git token for the desired GitHub account_**_
(as shown in the link https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)

-->You can generate a personal access token on GitHub in the following way:

-->Navigate to your Git account settings, then Developer Settings. Click the Personal access tokens menu, then click Generate new token.

-->Select repo as the scope. The token will be applicable for all the specified actions in your repositories.

-->Click Generate Token.

-->GitHub will display the personal access token only once. Ensure that you copy the token and store it in a safe space.

**2-Step 2: Open application.properties**

in this section:
____**# [Personal access token]____**
oauth.token=**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**

replace my token with the desired token (or if you prefer when the application starts type N1k0l1n to display my CV)

**3-Step 3 : Start the application (mvn spring-boot:run) or press play in Application :D**


*-Project Structure
SDK: corretto-11
Language level (SDK default (11))
