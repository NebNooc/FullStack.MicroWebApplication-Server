import { Component, OnInit } from '@angular/core';
import { User } from 'src/model/user';
import { TransactionService } from 'src/service/transaction.service';
import { Account } from '../../model/account';
import { AccountServiceService } from '../../service/account-service.service';
import { UserService } from 'src/service/user.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  account: Account;
  accounts: Account[];
  userAccounts: Account[];
  currentAccountTo: Account;
  user: User;

  constructor(private accountServiceService: AccountServiceService,
              private userService: UserService,
              private transactionService: TransactionService) { }

  ngOnInit() {
    this.getAccounts();
    this.getUser();
  }

  getAccounts() {
    this.accountServiceService.getAccounts().subscribe(accounts => this.accounts = accounts);
  }

  getUserAccounts() {
    this.accountServiceService.getAccountsByUser(this.user.id).subscribe(
        userAccounts => this.userAccounts = userAccounts);
  }

  setCurrentAccountTo(acc: Account): void {
    this.currentAccountTo = acc;
  }

  getUser() {
    this.user = this.userService.getUser();
    this.getUserAccounts();
  }

  depositFunds(): void {

    const accountId: number = this.currentAccountTo.id;
    const amount: number = +((document.getElementById('amount') as HTMLInputElement).value);

    this.transactionService.deposit(accountId, amount, +(this.user.id)).subscribe(() => this.getAccounts());
}
}
