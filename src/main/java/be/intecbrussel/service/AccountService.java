package be.intecbrussel.service;

import be.intecbrussel.model.Account;
import be.intecbrussel.repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();
    public boolean createAccount(Account account){
        return accountRepository.createAccount(account);
    }

    public boolean getAccount(String email, String passw){

        return accountRepository.getAccount(email).get().getPassw().equals(passw);
    }
}
