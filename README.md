# Applicant Intake CLI

Applicant Intake CLI is a Java command-line application for collecting, storing, searching, reviewing, and listing applicant admission forms. It was created as a backend engineering beginner-track deliverable for FlexiSAF Edusoft.

The application provides an interactive terminal workflow for applicant intake and reviewer operations. Application records are stored locally as formatted JSON in `data/applications.json`.

## Features

- Collects applicant personal, academic, and guardian information.
- Validates applicant email addresses and prevents duplicate applicant emails.
- Validates GPA values from `0.0` through `5.0`.
- Generates an application ID for each new application.
- Saves and loads applications from a JSON file using Jackson.
- Searches for applications by ID or email address.
- Lists applications in pages of five records.
- Allows reviewers to approve or reject applications.
- Displays an application summary with pending, approved, rejected, and acceptance-rate totals.
- Handles invalid numeric input without crashing the application.
- Reports repository and input failures with user-facing messages.

## Requirements

- Java Development Kit (JDK) 17 or newer.
- Apache Maven 3.x.
- A terminal or command prompt.

Verify the tools are available:

```bash
java -version
mvn -version
```

## Running the Application

Run all commands from the repository root, the directory containing `pom.xml`:

```bash
cd applicant-intake-cli
mvn clean compile
mvn exec:java
```

The Maven `exec-maven-plugin` is configured with `com.applicant.intake.Main` as its default main class. The fully qualified class name can also be supplied explicitly:

```bash
mvn org.codehaus.mojo:exec-maven-plugin:3.6.3:java \
	-Dexec.mainClass=com.applicant.intake.Main
```

The program uses standard input and standard output. Keep the terminal open while completing a form or navigating a menu.

## Application Workflow

### Main menu

1. **Apply**: collect and save a new application.
2. **Search Application**: search by application ID or email address.
3. **Reviewer Mode**: review an application or view the application summary.
4. **List Applications**: display all saved applications using pagination.
5. **Exit**: close the application.

### Applying

The applicant is asked for:

- First name
- Last name
- Email address
- Program
- University or college
- GPA
- Guardian full name
- Guardian contact
- Guardian email

Email input is normalized to lowercase and checked for a valid format and duplicate use. GPA input is read as a number and must be between `0.0` and `5.0`, inclusive. Invalid numeric input is reported and requested again.

New applications start with `PENDING` status. After a successful save, the generated application ID is displayed.

### Searching

The search menu supports:

- Search by application ID.
- Search by applicant email address.
- Return to the main menu.
- Exit.

When a match is found, the application details, academic score, status, and guardian information are displayed. A missing match produces a clear not-found message and returns to the search menu.

### Reviewer mode

Reviewer mode supports:

- **Review Application**: find an application by ID and approve or reject it.
- **View summary**: show total, pending, approved, rejected, and acceptance-rate counts.
- **Back**: return to the main menu.
- **Exit**: close the application.

Review decisions use the `ApplicationFormStatus` values `PENDING`, `APPROVED`, and `REJECTED`.

### Listing and pagination

The list view displays up to five applications per page. Use the following commands:

- `N`: next page.
- `P`: previous page.
- `B`: return to the main menu.

Invalid pagination commands are reported. Attempts to move beyond the first or last page are also reported without changing the current page.

## Error Handling and Input Behavior

Console input is centralized in `ConsoleInput`, which reads complete lines and parses numeric values explicitly. This avoids the common `Scanner.nextInt()`/`nextLine()` newline-buffer problem.

The application handles:

- Non-numeric menu choices with a retry message.
- Non-numeric GPA and reviewer choices with a retry message.
- GPA values outside the accepted range.
- Invalid or duplicate email addresses.
- Missing application records.
- Empty application data.
- JSON load and save failures.
- Missing data directories, which are created when saving if possible.
- End-of-input from a closed or redirected input stream.

Menu navigation uses loops instead of recursive retries, so repeated invalid input does not grow the call stack.

## Project Structure

```text
applicant-intake-cli/
├── pom.xml
├── README.md
├── data/
│   └── applications.json
└── src/
		└── main/
				└── java/
						└── com/applicant/intake/
								├── Main.java
								├── model/
								│   ├── ApplicationForm.java
								│   └── Menu.java
								├── service/
								│   ├── ApplicationRepository.java
								│   └── ApplicationService.java
								├── types/
								│   ├── ApplicationFormStatus.java
								│   └── IMenu.java
								├── ui/
								│   ├── MainMenu.java
								│   ├── ReviewerModeMenu.java
								│   └── SearchApplicationMenu.java
								└── util/
										├── ConsoleInput.java
										└── FormValidator.java
```

## Classes and Functions

### `com.applicant.intake.Main`

Application entry point.

- `main(String[] args)`: creates `MainMenu`, starts the menu loop, and reports an unexpected end of console input.

### `com.applicant.intake.model.ApplicationForm`

Domain model representing one applicant record. It contains applicant, academic, guardian, ID, and status fields.

- No-argument constructor: required by Jackson when loading JSON.
- Full constructor: initializes a new application as `PENDING` and creates its ID.
- Getter and setter methods: access or update form fields.
- `toString()`: returns a readable representation of the form.

The generated ID uses the format `app2026` followed by up to the first four characters of the email prefix and the application index, for example `app2026alim1`.

### `com.applicant.intake.model.Menu`

Abstract base class shared by the interactive menus.

- `getUserOption()`: reads a menu option through `ConsoleInput`.
- `displayMenu()`: abstract method implemented by each menu.
- `handleOption(int option)`: abstract method for menu-specific actions.
- `loadMenu()`: repeatedly displays the menu and handles choices until the menu stops.
- `stopMenu()`: ends the current menu loop.

### `com.applicant.intake.service.ApplicationRepository`

Persistence component backed by `data/applications.json`.

- Constructor: creates the Jackson mapper and points it at the JSON file.
- `save(ApplicationForm application)`: loads existing records, appends a record, creates the parent directory if needed, and writes the JSON file.
- `load()`: returns all records or an empty list when the file does not exist.
- `removeById(String id)`: removes a matching record and writes the updated list.

Repository methods can throw `IOException`; callers display a clear error and stop the affected operation.

### `com.applicant.intake.service.ApplicationService`

Application use-case service. Its methods coordinate console input, validation, persistence, and output.

- `createApplication()`: collects and validates a new form, generates an ID, and saves it.
- `searchApplicationById()`: loads and displays a matching record by ID.
- `searchApplicationByEmail()`: loads and displays a matching record by email.
- `reviewApplication()`: loads a record, changes its status to approved or rejected, and persists the decision.
- `listApplications()`: loads records and displays them five at a time with next, previous, and back commands.
- `viewSummary()`: calculates and displays application-status totals and the acceptance rate.

### `com.applicant.intake.ui.MainMenu`

Displays the top-level menu and routes choices to application, search, reviewer, list, or exit actions.

### `com.applicant.intake.ui.SearchApplicationMenu`

Displays search choices and routes to ID search, email search, back, or exit.

### `com.applicant.intake.ui.ReviewerModeMenu`

Displays reviewer choices and routes to review, summary, back, or exit.

### `com.applicant.intake.util.ConsoleInput`

Shared console input utility.

- `readLine(String prompt)`: prints a prompt and returns a trimmed line.
- `readInt(String prompt)`: repeatedly reads until a valid whole number is entered.
- `readDouble(String prompt)`: repeatedly reads until a valid decimal number is entered.

### `com.applicant.intake.util.FormValidator`

Validation utility.

- `isValidEmail(String email)`: checks that an email is non-null and matches the project email pattern.
- `isValidGpa(double gpa)`: checks that GPA is between `0.0` and `5.0`.
- `doesEmailExist(String email)`: checks persisted applications for a matching email.

### `com.applicant.intake.types.ApplicationFormStatus`

Enum containing the supported application states:

- `PENDING`
- `APPROVED`
- `REJECTED`

### `com.applicant.intake.types.IMenu`

Interface defining the general menu operations. It is retained as part of the project structure, while the current menu implementation shares behavior through the abstract `Menu` class.

## Data Storage

Records are stored in `data/applications.json` as a JSON array. Each object contains:

```json
{
	"firstName": "applicant first name",
	"lastName": "applicant last name",
	"email": "applicant@example.com",
	"program": "computer science",
	"university": "example university",
	"gpa": 4.5,
	"guardianName": "guardian name",
	"guardianContact": "phone number",
	"guardianEmail": "guardian@example.com",
	"id": "app2026appl1",
	"status": "PENDING"
}
```

The data path is relative to the process working directory. Run Maven from the repository root to use the included `data/applications.json` file.

## Maven Configuration

The project is identified as:

- Group ID: `com.applicant`
- Artifact ID: `applicant-intake-cli`
- Version: `1.0-SNAPSHOT`
- Java source and target: `17`

The main dependency is `jackson-databind`, used to serialize and deserialize application records. The `exec-maven-plugin` runs `com.applicant.intake.Main`.

## Development Checks

Compile the project after making changes:

```bash
mvn clean compile
```

Run the application:

```bash
mvn exec:java
```
