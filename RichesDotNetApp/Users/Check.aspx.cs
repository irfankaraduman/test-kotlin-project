 using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using RichesDotNetApp.Layer;

namespace RichesDotNetApp
{
    public partial class Users_Check : System.Web.UI.Page
    {
        protected void Page_PreInit(object sender, EventArgs e)
        {
            // Based on the users roll, use the appropriate master (which includes more or less menu items)
            if (User.IsInRole("admin"))
            {
                this.Page.MasterPageFile = "~/Admin/AdminMaster.master";
            }
            else
            {
                this.Page.MasterPageFile = "~/Users/UsersMaster.master";
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            String userName = User.Identity.Name;
            String accountNo = AccountDB.getFirstAccount(userName);
            Double balance = AccountDB.getBalance(accountNo);
            String Ccn = AccountDB.getCcn(accountNo);
            if (balance < Convert.ToDouble(AmountTextBox.Text))
            {
                OutputLabel.Text = "Not enough funds available";
                return;
            }
            else
            {
                Response.AppendToLog("check from: " + accountNo + " using the credit card on file: " + Ccn);

                Double amount = Convert.ToDouble(AmountTextBox.Text);
                Double newBalance = balance - amount;
                AccountDB.updateBalance(accountNo, newBalance);
                TransactionDB.addTransaction(accountNo, MemoTextBox.Text, amount, null);
                OutputLabel.Text = "Check Sent";
            }
        }
    }
}