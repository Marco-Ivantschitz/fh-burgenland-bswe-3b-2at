# FH Burgenland - Software Engineering - Software Management II - 2AT Examination

This repository contains your starting point for your 2AT examination.

---

## Getting Started

- Fork this repository to a public repository in your account.
- Add me as a collaborator.
- Copy the [issue #2](https://github.com/muhlba91/fh-burgenland-bswe-3b-2at/issues/2) in this repository into a new issue in your forked repository.
- Check out your forked repository, and start working. :)

### Environment

This Java Spring Boot application uses Gradle and requires Java 21.
A Gradle Wrapper is present (`gradlew(.bat)`).

### Useful Commands

- `./gradlew bootRun`: start the application locally
- `./gradlew test`: run the unit tests
- `./gradlew check`: run all checks defined in Gradle (plugins)
- `./gradlew checkstyleMain checkstyleTest`: run Checkstyle on main and test files
- `./gradlew bootJar`: create the runnable Jar file

---

## Assignment

### Commit Hygiene

- Every commit message must follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification.
- "Merge commits" must not be created. (You can also disable them for pull requests in your repository settings.)
- 1 "issue/task" ~ 1 commit ~ 1 pull request
  - Exceptions for pull requests need to be explained but are permitted, e.g., fixing code style errors when enabling Checkstyle.
  - Keep the commit size (number of files/lines changed) small and easily reviewable (exception: unit tests, code style).
  - Use squashing before merging a pull request.

Please note that I have provided a file, `.conform.yaml`, which you can use together with [conform](https://github.com/siderolabs/conform) to validate your commit messages (`conform enforce`). The tool will validate the last commit message on the current branch.

### Issues

You can use the provided issue template (name: `Issue`) for any issue you must create.
If you do not want to use it, please delete the directory `.github/ISSUE_TEMPLATE`.
If you use it, please put your GitHub handle as an assignee into the template before you create the first issue (search for `muhlba91` and replace it).
You can push this change directly to `main` without a pull request.

Issues must be detailed enough to be understood by an external developer and your technical product owner (a.k.a. the product owner responsible for the product in this repository).

If you find bugs in the code, please create an issue, work on it, and close it with a commit/pull request.

You are required to create an issue for each task of this assignment. (Yes, there will be few issues!)
They will not be "feature encapsulated" but focus more on each topic, like `Unit Tests for Service X`, `Enable Checkstyle`, `Implement Pipeline Job X`, `Fix X` ...
However, take care of best practices like splitting issues, if necessary.

Create an issue for everything!

### Branch Protections & Pull Requests

- Enable branch protection for `main`.
- Require pull requests before merging. (Do not require approvals. You need to be able to merge pull requests by yourself.)
- Require status checks to pass and branches to be up to date.
- Require linear history.
- See the `Pipeline` section for additional actions.

Create a pull request for everything! (Exceptions are outlined in the corresponding section.)

You must use the Pull Request template provided in `.github/pull_request_template.md`.

### Javadoc

You copied over an already existing issue in the `Getting Started` section.
You are expected to work on it and close it (in your forked repository).
Please modify the issue according to best practices, if necessary.

### Tests

- Create standard unit tests (the success path and, where applicable, the error path) for each method in
  - the `person` service, and
  - the `note` service.
- Write (at least) three controller tests covering the most critical functionality.
- Think about one edge case per service.
  - You do not need to implement a test case for it!
  - If you write a paper, you must include a description of your edge cases.
- Fix bugs you encounter.

### Code Style

- Add Checkstyle as a syntax checker.
- Use the configuration file at `config/checkstyle/checkstyle.xml` (default location).
- Expand the configuration file to detect missing override annotations.
- Fix any errors the tool finds.

If you write a paper, you must include your findings/learnings.
(Do not mention every line you changed but an overview of the findings/rules and why it is an important rule.)

### Containerization & Deployment

You must create a `Dockerfile` which is optimized for your pipeline.

Please think about possible deployment options and their advantages and disadvantages.
If you write a paper, you must explain these options, and conclude what your choice is for this project.

### Pipeline

- Create a pipeline containing the following jobs:
  - Test
  - Lint (Checkstyle, Hadolint)
  - Build (Runnable Jar File) - assumption: build lasts 1 hour
  - Container
    - Use the GitHub Container Registry (GHCR) to push the container to.
- Set the correct job dependencies and under what conditions the jobs are executed.
- Ensure your pipeline runs through at least three commits (on `main`) without errors.
- Enable branch protection required checks on `main` to include all jobs.
  - Hint: the pipeline must have run on `main` before checks can be enabled.

If you write a paper, explain how you would integrate the deployment into your pipeline.
If you have multiple options, shortly explain each of them and compare with the preferred one.

### OpenAPI Specification

Create an OpenAPI specification file for the `/person/*` endpoints. You only need to map HTTP 200 responses and can simplify the `notes` property to be an array of strings.
You do not need to write long descriptions; keep it short and crisp but clear for every developer looking at it.

Place the specification in `/docs/openapi.yml`.

### README & LICENSE

If you write a paper, also create a README file containing best practice sections as discussed in the lectures (and required for the original assignment).

If you write a paper, please explain some reasons on why the MIT license was chosen, what other type of licenses there are, and elaborate shortly on whether you were also able to choose GPL instead.

---

## Submission

The deadline is communicated by e-mail.
Please submit your repository by creating a new issue in this (original) repository using the `Assignment Submission` template before the deadline ends.

After you submit your work (a.k.a. created the submission issue), no changes are permitted in the repository!

## Paper

Please refer to the original assignment specification for the requirements of your paper. You can submit up to 20 pages.

Please note that you can opt for a discussion instead of a paper.

## Presentation

Please refer to the original assignment specification for the requirements of your presentation. I expect you to showcase your work in a "live environment".

Please note that you can opt for a discussion instead of a presentation.

## Discussion

If you opt for a discussion, we will review your submission and talk about your work. You need to understand the concepts, links between the topics, and what you are/would be doing to achieve specific goals based on your work/the assignment.

Please take care that parts only required in the paper (see above in each section) will be part of our discussion. I encourage you to think about these topics.

---

## Questions

If you have any questions about the assignment, please feel free to get in touch.
