/*
 * @ (#) Course.java   1.0    8/29/2024
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

public class Course {
    private String id;
    private String title;
    private int  credit;
    private String department;

    public Course(){
        this("","",0,"");
    }

    public Course(String id, String title, int credit, String department) {
        this.id = id;
        this.title = title;
        this.credit = credit;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id==null || id.length()<3){
            throw new IllegalArgumentException("ID must have at least 3 characters");
        }
        if(id.matches("[a-zA-Z0-9]+")){
            throw new IllegalArgumentException("ID must contain only letters or digits");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        this.title = title;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        if (credit <= 0) {
            throw new IllegalArgumentException("Credit must be greater than 0");
        }
        this.credit = credit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10d %-20s\n", id, title, credit, department);
    }
}

