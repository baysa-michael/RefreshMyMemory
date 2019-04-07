package com.refreshmymemory.view_presenter.login;

import com.refreshmymemory.utilities.Hashing;

class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";

    @Override
    public int obtainSalt(String username) {
        // IMPLEMENT SERVER SIDE REQUEST FOR SALT *****************
        return 6521;
    }

    @Override
    public boolean login(String username, String password) {
        // Obtain Salt by Username *******************  Need to implement server request
        int userSalt = obtainSalt(username);

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
