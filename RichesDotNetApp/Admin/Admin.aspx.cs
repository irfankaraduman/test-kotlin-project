using System;
using System.Diagnostics;
using RichesDotNetApp.Layer;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Configuration;

namespace RichesDotNetApp.Admin
{
    public partial class Admin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            String Receiver = MessageDB.InsertMessage(User.Identity.Name, EmailDropDown.SelectedValue, SeverityTextBox.Text, SubjectTextBox.Text, BodyTextBox.Text);
            SendMail(Receiver, SeverityTextBox.Text, SubjectTextBox.Text, BodyTextBox.Text);
        }
        protected void SendMail(String receiver, String severity, String subject, String body)
        {
            String error = "";
            String output = "";
            if (receiver != null && !receiver.Equals(""))
            {
                {
                    Process p = new Process();
                    p.StartInfo.FileName = "cmd.exe";
                    p.StartInfo.Arguments = "/C \"echo \"" + severity + subject + body + "\" > NUL \"";
                    p.StartInfo.WindowStyle = ProcessWindowStyle.Hidden;
                    p.StartInfo.UseShellExecute = false;
                    p.StartInfo.RedirectStandardError = true;
                    p.StartInfo.RedirectStandardOutput = true;
                    p.Start();
                    error += p.StandardError.ReadToEnd();
                    output += p.StandardOutput.ReadToEnd();
                    p.WaitForExit();
                }
                {
                    Process p = new Process();
                    p.StartInfo.FileName = "cmd.exe";
                    p.StartInfo.Arguments = "/C \"echo Message sent to " + receiver + "\"";
                    p.StartInfo.WindowStyle = ProcessWindowStyle.Hidden;
                    p.StartInfo.UseShellExecute = false;
                    p.StartInfo.RedirectStandardError = true;
                    p.StartInfo.RedirectStandardOutput = true;
                    p.Start();
                    error += p.StandardError.ReadToEnd();
                    output += p.StandardOutput.ReadToEnd();
                    p.WaitForExit();
                }

                OutputLabel.Text = error;
                ErrorLabel.Text = output;
            }
        }
    }
}