using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;
using System.Data.SqlServerCe;
using System.Configuration;
using RichesDotNetApp.Layer;

namespace RichesDotNetApp.Layer
{
    public class MessageDB
    {
        public static String InsertMessage(String Sender, String eMail, String severity, String subject, String body)
        {
            String userName = MembershipDB.getUsernameForEmail(eMail);
            //using (OleDbConnection connection = new OleDbConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            //{
            //    connection.Open();
            //    OleDbCommand query = new OleDbCommand("INSERT INTO message (username,date,sender,severity,subject,body) Values(@Username,@Date,@Sender,@Severity,@Subject,@Body)", connection);
            //    query.Parameters.AddWithValue("@Username", userName);
            //    query.Parameters.AddWithValue("@Date", DateTime.Today);
            //    query.Parameters.AddWithValue("@Sender", Sender);
            //    query.Parameters.AddWithValue("@Severity", severity);
            //    query.Parameters.AddWithValue("@Subject", subject);
            //    query.Parameters.AddWithValue("@Body", body);

            //    query.ExecuteNonQuery();

            //    return userName;
            //}



            using (SqlCeConnection connection = new SqlCeConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            {
                connection.Open();
                SqlCeCommand query = new SqlCeCommand("INSERT INTO message (username,date,sender,severity,subject,body) Values(@Username,@Date,@Sender,@Severity,@Subject,@Body)", connection);
                query.Parameters.AddWithValue("@Username", userName);
                query.Parameters.AddWithValue("@Date", DateTime.Today);
                query.Parameters.AddWithValue("@Sender", Sender);
                query.Parameters.AddWithValue("@Severity", severity);
                query.Parameters.AddWithValue("@Subject", subject);
                query.Parameters.AddWithValue("@Body", body);

                query.ExecuteNonQuery();

                return userName;
            }
        }

    }
}