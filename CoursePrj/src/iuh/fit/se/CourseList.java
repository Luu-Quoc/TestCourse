/*
 * @ (#) CourseList.java   1.0    8/29/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit.se;

/*
 * @desscription:
 * @author: Quoc Luu
 * @version: 1.0
 * @date: 8/29/2024
 */

import java.util.*;

/**
 * CourseList class to manage a list of courses.
 * Provides methods to add, remove, find, and sort courses.
 */
public class CourseList {
    private Course[] courses;
    private static int count = 0;

    /**
     * Constructor to initialize the CourseList with a specified size.
     *
     * @param n The maximum number of courses.
     * @throws IllegalArgumentException if the size is less than or equal to 0.
     */
    public CourseList(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Length of the array must be greater than 0");
        }
        courses = new Course[n];
    }

    /**
     * Adds a course to the list.
     *
     * @param c The course to add.
     * @return true if the course was added successfully, false otherwise.
     */
    public boolean addCourse(Course c) {
        if (c == null || count >= courses.length || exists(c)) {
            return false;
        }
        courses[count++] = c;
        return true;
    }

    /**
     * Checks if a course already exists in the list.
     *
     * @param c The course to check.
     * @return true if the course exists, false otherwise.
     */
    private boolean exists(Course c) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equals(c.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets an array of all courses.
     *
     * @return A copy of the courses array up to the current count.
     */
    public Course[] getCourses() {
        return Arrays.copyOf(courses, count);
    }

    /**
     * Removes a course by ID.
     *
     * @param id The ID of the course to remove.
     * @return true if the course was removed successfully, false otherwise.
     */
    public boolean removeCourse(String id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equals(id)) {
                System.arraycopy(courses, i + 1, courses, i, count - i - 1);
                courses[--count] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a course by ID.
     *
     * @param id The ID of the course to find.
     * @return The course if found, or null if not found.
     */
    public Course findCourseById(String id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equals(id)) {
                return courses[i];
            }
        }
        return null;
    }

    /**
     * Finds courses by title.
     *
     * @param title The title to search for.
     * @return A list of courses that match the title, or null if no courses are found.
     */
    public List<Course> findCoursesByTitle(String title) {
        List<Course> foundCourses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (courses[i].getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundCourses.add(courses[i]);
            }
        }
        return foundCourses.isEmpty() ? null : foundCourses;
    }

    /**
     * Finds courses by department.
     *
     * @param department The department to search for.
     * @return A list of courses in the specified department, or null if no courses are found.
     */
    public List<Course> findCoursesByDepartment(String department) {
        List<Course> foundCourses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (courses[i].getDepartment().equalsIgnoreCase(department)) {
                foundCourses.add(courses[i]);
            }
        }
        return foundCourses.isEmpty() ? null : foundCourses;
    }

    /**
     * Gets courses sorted by title.
     *
     * @return A list of courses sorted by title.
     */
    public List<Course> getSortedCoursesByName() {
        List<Course> sortedCourses = new ArrayList<>(Arrays.asList(courses).subList(0, count));
        Collections.sort(sortedCourses, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                return c1.getTitle().compareToIgnoreCase(c2.getTitle());
            }
        });
        return sortedCourses;
    }

    /**
     * Finds courses with the maximum credit value.
     *
     * @return A list of courses with the maximum credit.
     */
    public List<Course> findCoursesWithMaxCredit() {
        List<Course> maxCreditCourses = new ArrayList<>();

        if (count == 0) {
            return maxCreditCourses; // Return empty list if there are no courses
        }

        int maxCredit = Integer.MIN_VALUE;

        // Find the maximum credit value
        for (int i = 0; i < count; i++) {
            if (courses[i] != null && courses[i].getCredit() > maxCredit) {
                maxCredit = courses[i].getCredit();
            }
        }

        // Collect all courses with the maximum credit value
        for (int i = 0; i < count; i++) {
            if (courses[i] != null && courses[i].getCredit() == maxCredit) {
                maxCreditCourses.add(courses[i]);
            }
        }
        return maxCreditCourses;
    }

    /**
     * Finds the department with the most courses.
     *
     * @return The department with the most courses, or a message if no courses are available.
     */
    public String findDepartmentWithMostCourses() {
        if (count == 0) {
            return "No courses available"; // Return a message if there are no courses
        }

        Map<String, Integer> departmentCounts = new HashMap<>();

        // Count courses by department
        for (int i = 0; i < count; i++) {
            if (courses[i] != null) {
                String department = courses[i].getDepartment();
                departmentCounts.put(department, departmentCounts.getOrDefault(department, 0) + 1);
            }
        }

        // Find the department with the most courses
        String maxDepartment = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : departmentCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxDepartment = entry.getKey();
            }
        }

        return maxDepartment != null ? maxDepartment : "No department found";
    }
}

