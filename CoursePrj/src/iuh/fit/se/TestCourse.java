/*
 * @ (#) TestCourse.java   1.0    8/29/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit.se;

import java.util.List;
import java.util.Scanner;

/*
 * @desscription:
 * @author: Quoc Luu
 * @version: 1.0
 * @date: 8/29/2024
 */

import java.util.*;


public class TestCourse {
    public static void main(String[] args) {
        // Create a course list with a maximum size of 10
        CourseList courseList = new CourseList(10);
        Scanner scanner = new Scanner(System.in);
        initData(courseList);
        while (true) {
            // Display the menu for the user
            System.out.println("\nCourse Management Menu:");
            System.out.println("1. Add a course");
            System.out.println("2. Display all courses");
            System.out.println("3. Remove a course by ID");
            System.out.println("4. Find a course by ID");
            System.out.println("5. Find a course by Title");
            System.out.println("6. Find a course by Department");
            System.out.println("7. Sort Courses By Title");
            System.out.println("8. Find Courses with Max Credit");
            System.out.println("9. Find Department with Most Courses ");
            System.out.println("10. Exit");
            System.out.print("Choose an option (1-10): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            switch (choice) {
                case 1:
                    // Add a Course
                    System.out.print("Enter course ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter course credit: ");
                    int credit = scanner.nextInt();
                    scanner.nextLine();  // Clear the buffer
                    System.out.print("Enter course department: ");
                    String department = scanner.nextLine();

                    Course newCourse = new Course(id, title, credit, department);
                    boolean added = courseList.addCourse(newCourse);
                    System.out.println("Add " + id + ": " + (added ? "Success" : "Failed (course already exists)"));
                    break;

                case 2:
                    // Show all Courses
                    System.out.println("\nAll courses:");
                    System.out.printf("%-10s %-30s %-10s\t %-20s\n", "ID", "Title", "Credit", "Department");
                    for (Course course : courseList.getCourses()) {
                        System.out.printf("%-10s %-30s %-10d\t %-20s\n", course.getId(), course.getTitle(), course.getCredit(), course.getDepartment());
                    }
                    break;

                case 3:
                    // Remove Course by ID
                    System.out.print("Enter course ID to remove: ");
                    String removeId = scanner.nextLine();
                    boolean removed = courseList.removeCourse(removeId);
                    System.out.println("Remove " + removeId + ": " + (removed ? "Success" : "Failed (course not found)"));
                    break;

                case 4:
                    // Find Course by ID
                    System.out.print("Enter course ID to find: ");
                    String searchId = scanner.nextLine();
                    Course foundCourse = courseList.findCourseById(searchId);
                    if (foundCourse != null) {
                        System.out.println("Found course - ID: " + foundCourse.getId() + ", Title: " + foundCourse.getTitle());
                    } else {
                        System.out.println("Course ID not found.");
                    }
                    break;

                case 5:
                    // Find Course by Title
                    System.out.print("Enter course Title to find: ");
                    String searchTitle = scanner.nextLine();
                    List<Course> foundCoursesByTitle = courseList.findCoursesByTitle(searchTitle);
                    if (foundCoursesByTitle != null) {
                        System.out.println("\nCourses found with title containing \"" + searchTitle + "\":");
                        printCourses(foundCoursesByTitle);
                    } else {
                        System.out.println("No courses found with the title containing \"" + searchTitle + "\".");
                    }
                    break;

                case 6:
                    // Find Course by Department
                    System.out.print("Enter course Department to find: ");
                    String searchDepartment = scanner.nextLine();
                    List<Course> foundCoursesByDepartment = courseList.findCoursesByDepartment(searchDepartment);
                    if (foundCoursesByDepartment != null) {
                        System.out.println("\nCourses found in department \"" + searchDepartment + "\":");
                        printCourses(foundCoursesByDepartment);
                    } else {
                        System.out.println("No courses found in department \"" + searchDepartment + "\".");
                    }
                    break;

                case 7:
                    // Sort Courses By Title
                    List<Course> sortedCourses = courseList.getSortedCoursesByName();
                    if (sortedCourses != null) {
                        System.out.println("\nCourses sorted by title:");
                        printCourses(sortedCourses);
                    } else {
                        System.out.println("No courses available to sort.");
                    }
                    break;
                case 8:
                    // Find Courses with Max Credit
                    List<Course> maxCreditCourses = courseList.findCoursesWithMaxCredit();
                    if (!maxCreditCourses.isEmpty()) {
                        System.out.println("\nCourses with the maximum credit:");
                        printCourses(maxCreditCourses);
                    } else {
                        System.out.println("No courses found with maximum credit.");
                    }
                    break;
                case 9:
                    // Find Department with Most Courses
                    String departmentWithMostCourses = courseList.findDepartmentWithMostCourses();
                    System.out.println("Department with most courses: " + departmentWithMostCourses);
                    break;
                case 10:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please choose between 1 and 8.");
            }
        }
    }

    // print Course
    private static void printCourses(List<Course> courses) {
        if (courses != null && !courses.isEmpty()) {
            System.out.printf("%-10s %-30s %-10s\t %-20s\n", "ID", "Title", "Credit", "Department");
            for (Course course : courses) {
                System.out.printf("%-10s %-30s %-10d\t %-20s\n", course.getId(), course.getTitle(), course.getCredit(), course.getDepartment());
            }
        } else {
            System.out.println("No courses to display.");
        }
    }
    private static void initData(CourseList courseList) {
        Course course1 = new Course("FIT101", "Java Programming", 3, "FIT");
        Course course2 = new Course("FIT102", "Web Programming", 3, "FIT");
        Course course3 = new Course("FIT103", "Database Programming", 3, "FIT");
        Course course4 = new Course("FIT104", "Mobile Programming", 3, "FIT");
        Course course5 = new Course("FIT105", "Software Engineering", 3, "FIT");
        Course course6 = new Course("FIT106", "Data Science", 3, "FIT");
        Course course7 = new Course("FIT107", "Machine Learning", 3, "FIT");
        Course course8 = new Course("FIT108", "Artificial Intelligence", 3, "FIT");

        courseList.addCourse(course1);
        courseList.addCourse(course2);
        courseList.addCourse(course3);
        courseList.addCourse(course4);
        courseList.addCourse(course5);
        courseList.addCourse(course6);
        courseList.addCourse(course7);
        courseList.addCourse(course8);
    }
}
