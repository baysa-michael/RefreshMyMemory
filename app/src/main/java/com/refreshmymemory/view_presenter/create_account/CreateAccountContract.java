package com.refreshmymemory.view_presenter.create_account;

import java.util.Map;

public interface CreateAccountContract {
    interface View {
        void showConfirm();
        void showCancel();
        void informUser(String message);
    }

    interface Presenter {
        boolean validatePasswordRequirements(String password);
        Map<String, String> prepareCreateAccountRequest(String username, String password,
                                                        String email, String displayName);
    }
}
