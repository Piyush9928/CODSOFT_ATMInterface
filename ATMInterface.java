import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public double checkBalance() {
        return account.getBalance();
    }

    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }
}

public class ATMInterface extends JFrame implements ActionListener {
    private ATM atm;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton, depositButton, checkBalanceButton;

    public ATMInterface(ATM atm) {
        this.atm = atm;
        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel amountLabel = new JLabel("Enter amount:");
        amountLabel.setBounds(50, 50, 100, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(160, 50, 150, 30);
        add(amountField);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 100, 120, 30);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(190, 100, 120, 30);
        depositButton.addActionListener(this);
        add(depositButton);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(110, 150, 150, 30);
        checkBalanceButton.addActionListener(this);
        add(checkBalanceButton);

        balanceLabel = new JLabel("Balance: ");
        balanceLabel.setBounds(50, 200, 300, 30);
        add(balanceLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String input = amountField.getText();
        double amount;

        try {
            amount = Double.parseDouble(input);

            if (action.equals("Withdraw")) {
                if (atm.withdraw(amount)) {
                    JOptionPane.showMessageDialog(this, "Withdrawal Successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance!");
                }
            } else if (action.equals("Deposit")) {
                atm.deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposit Successful!");
            }
        } catch (NumberFormatException ex) {
            if (action.equals("Check Balance")) {
                balanceLabel.setText("Balance: " + atm.checkBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000); // Initial balance
        ATM atm = new ATM(account);
        new ATMInterface(atm);
    }
}
