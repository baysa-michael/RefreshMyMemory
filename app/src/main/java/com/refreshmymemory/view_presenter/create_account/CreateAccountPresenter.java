package com.refreshmymemory.view_presenter.create_account;

import com.refreshmymemory.control.Hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class CreateAccountPresenter implements CreateAccountContract.Presenter {
    @Override
    public boolean validatePasswordRequirements(String password) {
        // A minimum of 14 characters with at least one capital and one digit
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(\\S){14,}$");
    }

    @Override
    public Map<String, String> prepareCreateAccountRequest(String username, String password, String email,
                                               String displayName) {
        // Create User Salt
        Random r = new Random();
        int salt = r.nextInt();

        // Hash Password
        String userData = Integer.toString(salt) +
                username +
                Integer.toString(salt) +
                password +
                Integer.toString(salt);
        String hashedPassword;
        try {
            hashedPassword = Hashing.calculateHash(userData);
        } catch (Exception e) {
            // Return failure
            e.printStackTrace();
            return null;
        }

        // Create Hashmap of User Data
        Map<String, String> requestData = new HashMap<>();
        requestData.put("requestType", "createAccount");
        requestData.put("username", username);
        requestData.put("hashedPassword", hashedPassword);
        requestData.put("email", email);
        requestData.put("displayName", displayName);
        requestData.put("salt", Integer.toString(salt));

        // Return Hahmap for call to AsyncTask
        return requestData;
    }
}
