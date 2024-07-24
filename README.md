The provided code is a simple console-based Online Examination System implemented in Java. It includes user registration, login, profile viewing, and conducting an exam with multiple-choice questions. Here's a summary of each part:

Main Class: `OnlineExamination`
1. **Initialization:**
   - Creates a `Scanner` object for user input.
   - Instantiates `UserManagement` and `Examination` objects.
   - Adds sample questions to the `Examination` object.

2. **Main Menu Loop:**
   - Displays different menus based on whether a user is logged in.
   - If no user is logged in, options to register, login, or exit are displayed.
   - If a user is logged in, options to start the exam, view score, view profile, or logout are displayed.

3. **User Choices:**
   - Depending on the user's choice, appropriate methods from `UserManagement` or `Examination` are called.
   - Registration and login return `User` objects which are used to maintain the session.
   - The program runs until the user chooses to exit.

### Class: `User`
- Represents a user with fields for `username`, `password`, `name`, and `age`.
- Provides getter methods for these fields.

### Class: `UserManagement`
- Manages user registration and login.
- Stores registered users in a `HashMap`.
- Provides methods to register a new user, login an existing user, and view a user's profile.

### Class: `Question`
- Represents a single multiple-choice question with fields for the question text, options, and the correct answer.
- Provides getter methods for these fields.

### Class: `Examination`
- Manages the exam questions and user scores.
- Stores questions in an `ArrayList` and user scores in a `HashMap`.
- Provides methods to add questions, conduct the exam, get a user's score, and get the total number of questions.

### Detailed Workflow:
1. **Registration:**
   - User enters a username, password, name, and age.
   - If the username is not already taken, the user is registered and added to the `users` map.

2. **Login:**
   - User enters a username and password.
   - If the credentials match an existing user, the user is logged in.

3. **Conducting the Exam:**
   - For each question, the user is prompted to enter an answer.
   - The user's score is calculated based on the number of correct answers and stored in `userScores`.

4. **Viewing Profile and Score:**
   - The user can view their profile details and their score for the exam.

