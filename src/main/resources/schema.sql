-- Active: 1682524878303@@127.0.0.1@3306@customer

CREATE SCHEMA IF NOT EXISTS customer;

SET NAMES 'UTF8MB4';

USE customer;

CREATE TABLE
    Users(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        first_name VARCHAR(50) NOT NULL,
        last_name VARCHAR(50) NOT NULL,
        email VARCHAR(50) NOT NULL,
        password VARCHAR(50) DEFAULT NULL,
        phone VARCHAR(20) DEFAULT NULL,
        address VARCHAR(255) DEFAULT NULL,
        title VARCHAR(20) DEFAULT NULL,
        bio VARCHAR(200) DEFAULT NULL,
        enabled BOOLEAN DEFAULT FALSE,
        non_locked BOOLEAN DEFAULT TRUE,
        using_mfa BOOLEAN DEFAULT FALSE,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
        image_url VARCHAR(200) DEFAULT '/Users/nascotech/Downloads/db7f40a0e75d9487b1d165c551e832af.jpg',
        CONSTRAINT UQ_Users_Email UNIQUE(email)
    );

CREATE TABLE
    Roles(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        permission VARCHAR(200) NOT NULL,
        CONSTRAINT UQ_Roles_Name UNIQUE(name)
    );

CREATE TABLE
    UserRoles(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT UNSIGNED not NULL,
        role_id BIGINT UNSIGNED not NULL,
        FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (role_id) REFERENCES Roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
        CONSTRAINT UQ_UserRoles_User_Id UNIQUE (user_id)
    );

CREATE TABLE
    Events(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        type VARCHAR(50) NOT NULL CHECK(
            type IN (
                'LOGIN_ATTEMPT',
                'LOGIN_ATTEMPT_FAILURE',
                'LOGIN_ATTEMPT_SUCCESS',
                'PROFILE_UPADATE',
                'PROFILE_PICTURE_UPDATE',
                'ROLE_UPDATE',
                'ACCOUNT_SETTINGS_UPDATE',
                'PASSWORD_UPDATE',
                'MFA_UPDATE'
            )
        ),
        description VARCHAR(200) NOT NULL,
        CONSTRAINT UQ_Events_Type UNIQUE (type)
    );

CREATE TABLE
    UserEvents(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT UNSIGNED NOT NULL,
        event_id BIGINT UNSIGNED NOT NULL,
        device VARCHAR(50) DEFAULT NULL,
        ip_address VARCHAR(100) DEFAULT NULL,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (event_id) REFERENCES Events (id) ON DELETE RESTRICT ON UPDATE CASCADE
    );

CREATE TABLE
    AccountVerifications(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT UNSIGNED NOT NULL,
        url VARCHAR(200) NOT NULL,
        FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT UQ_AccountVerifications_User_Id UNIQUE (user_id),
        CONSTRAINT UQ_AccountVerifications_Url UNIQUE (url)
    );

CREATE TABLE
    ResetPasswordVerifications(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT UNSIGNED NOT NULL,
        url VARCHAR(200) NOT NULL,
        expiration_date DATETIME NOT NULL,
        FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT UQ_ResetPasswordVerifications_Url UNIQUE(url),
        CONSTRAINT UQ_ResetPasswordVerifications_User_Id UNIQUE(user_id)
    );

CREATE TABLE
    TwoFactorVerifications(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT UNSIGNED NOT NULL,
        code VARCHAR(10) NOT NULL,
        expiration_date DATETIME NOT NULL,
        FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT UQ_TwoFactorVerifications_User_Id UNIQUE(user_id),
        CONSTRAINT UQ_TwoFactorVerifications_Code UNIQUE(code)
    )