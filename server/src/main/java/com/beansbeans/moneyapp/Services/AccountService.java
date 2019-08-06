package com.beansbeans.moneyapp.Services;

import com.beansbeans.moneyapp.Model.Account;
import com.beansbeans.moneyapp.Model.Transaction;
import com.beansbeans.moneyapp.Repositories.AccountRepository;
import com.beansbeans.moneyapp.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;


    public AccountService(AccountRepository accountRepository, @Autowired TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Iterable<Account> findAllAccountsByUserId(Long userid) {
//        ArrayList<Long> idList = new ArrayList<>();
//        idList.add(userid);
//        return accountRepository.findAllById(idList);
        return accountRepository.findAllByUserIdIs(userid);
    }

    public Account create(Account account){

        return accountRepository.save(account);
    }

    public Account show(Long id){
        return accountRepository.findById(id).get();
    }

    public Account updateBalance(Long id, Account newAccountData){
        Account originalAccount = accountRepository.findById(id).get();
        originalAccount.setBalance(newAccountData.getBalance());
        return accountRepository.save(originalAccount);
    }

    public Boolean deleteAccount(Long id){
        Iterable<Transaction> fromList = transactionRepository.findAllByFromAccountId(id);
        for(Transaction transaction: fromList){
            if(transaction.getToAccountId() != null){
                transaction.setFromAccountId(null);
                transactionRepository.save(transaction);
            }
            else{
                transactionRepository.delete(transaction);
            }
        }
        Iterable<Transaction> toList = transactionRepository.findAllByToAccountId(id);
        for(Transaction transaction: toList){
            if(transaction.getFromAccountId() != null){
                transaction.setToAccountId(null);
                transactionRepository.save(transaction);
            }
            else{
                transactionRepository.delete(transaction);
            }
        }
        accountRepository.deleteById(id);
        return true;
    }


}
