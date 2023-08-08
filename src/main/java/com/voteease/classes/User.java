package com.voteease.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private String account_id;
    private String user_type;
    private String email;
    private String password;
    private final String account_status = "Active";

    public User(String user_type, String email, String password) {
        this.user_type = user_type;
        this.email = email;
        this.password = password;
    }

    public User(String email , String password){

    }

    public User(){}

    public boolean checkEmailExistance(Connection con) throws Exception {
        String query = "SELECT * FROM  account WHERE email=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        return rs.next(); //email already exist
    }

    public void setAccountID(Connection con) throws Exception {
        String query = "SELECT * FROM  account WHERE email=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            this.account_id = rs.getString("account_id");
        }
    }

    public boolean registration(Connection con) throws Exception {
        String query = "INSERT INTO account (user_type, email, password, account_status) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, user_type);
        pstmt.setString(2, email);
        pstmt.setString(3, password);
        pstmt.setString(4, account_status);
        int a = pstmt.executeUpdate();
        return a > 0;
    }

    // getters
    public String getAccount_id() {
        return account_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccount_status() {
        return account_status;
    }

}
