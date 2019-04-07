package com.refreshmymemory.view_presenter.create_account;

public interface CreateAccountContract {
    interface View {
        void showConfirm();
        void showCancel();
        void informUser(String message);
    }

    interface Presenter {
        boolean validatePasswordRequirements(String password);
        boolean submitCreateAccountRequest(String username, String password, String email,
                                           String displayName);
    }
}
