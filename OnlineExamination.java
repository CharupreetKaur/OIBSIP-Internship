import java.util.*;
public class OnlineExamination {
    public static void main(String[] args) {
        // Step 1: Initialize the application
        Scanner scanner = new Scanner(System.in);
        UserManagement userManagement = new UserManagement();
        Examination exam = new Examination();
        
        // Add some sample questions to the exam
        exam.addQuestion(new Question("What is the capital of France?", new String[]{"1. Paris", "2. London", "3. Rome", "4. Berlin"}, 1));
        exam.addQuestion(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Venus", "3. Mars", "4. Jupiter"}, 3));
        exam.addQuestion(new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"1. Harper Lee", "2. Mark Twain", "3. J.K. Rowling", "4. Ernest Hemingway"}, 1));
        
        boolean exit = false;
        User currentUser = null;

        // Main menu loop
        while (!exit) {
            System.out.println("Online Examination System");
            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1 -> // Register a new user
                        currentUser = userManagement.register(scanner);
                    case 2 -> // Login
                        currentUser = userManagement.login(scanner);
                    case 3 -> {
                        // Exit
                        exit = true;
                        System.out.println("Thank you for using the Online Examination System. Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("1. Start Exam");
                System.out.println("2. View Score");
                System.out.println("3. View Profile");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1 -> // Start the exam
                        exam.conductExam(scanner, currentUser);
                    case 2 -> // View score
                        System.out.println("Your score is: " + exam.getScore(currentUser) + " out of " + exam.getTotalQuestions());
                    case 3 -> // View profile
                        userManagement.viewProfile(currentUser);
                    case 4 -> {
                        // Logout
                        currentUser = null;
                        System.out.println("You have been logged out.");
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
            System.out.println();
        }
        
        // Step 4: Close the scanner
        scanner.close();
    }
}

class User {
    private final String username;
    private final String password;
    private final String name;
    private final int age;

    public User(String username, String password, String name, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class UserManagement {
    private final HashMap<String, User> users;

    public UserManagement() {
        this.users = new HashMap<>();
    }

    public User register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose another username.");
            return null;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        User user = new User(username, password, name, age);
        users.put(username, user);
        System.out.println("Registration successful!");
        return user;
    }

    public User login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome " + user.getName());
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    public void viewProfile(User user) {
        System.out.println("User Profile");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
    }
}

class Question {
    private final String questionText;
    private final String[] options;
    private final int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Examination {
    private final ArrayList<Question> questions;
    private final HashMap<User, Integer> userScores;

    public Examination() {
        this.questions = new ArrayList<>();
        this.userScores = new HashMap<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void conductExam(Scanner scanner, User user) {
        int score = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }
            System.out.print("Enter your answer (1-4): ");
            int userAnswer = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            if (userAnswer == question.getCorrectAnswer()) {
                score++;
            }
            System.out.println();
        }
        userScores.put(user, score);
        System.out.println("Exam completed! Your score is: " + score + " out of " + questions.size());
    }

    public int getScore(User user) {
        return userScores.getOrDefault(user, 0);
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
