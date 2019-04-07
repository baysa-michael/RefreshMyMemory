package com.refreshmymemory.view_presenter.create_account;

import com.refreshmymemory.utilities.Hashing;

import java.util.Random;

class CreateAccountPresenter implements CreateAccountContract.Presenter {
    public boolean validatePasswordRequirements(String password) {
        // A minimum of 14 characters with at least one capital and one digit
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(\\S){14,}$");
    }

    public boolean submitCreateAccountRequest(String username, String password, String email,
                                       String displayName) {
        // Create User Salt
        Random r = new Random();
        int userSalt = r.nextInt();

        // Hash Password
        String userData = Integer.toString(userSalt) +
                username +
                Integer.toString(userSalt) +
                password +
                Integer.toString(userSalt);
        try {
            String hashedPassword = Hashing.calculateHash(userData);
        } catch (Exception e) {
            // Return failure
            e.printStackTrace();
            return false;
        }

        // Send request to server  ***************************


        // Return success or failure  ***************************
        return true;
    }
}
