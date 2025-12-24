using System;
using System.Data.OleDb;
using System.Configuration;
using System.Data.SqlServerCe;

namespace RichesDotNetApp.Layer
{
    public class ProfileDB
    {
        public void InsertProfile(String userName, String firstName, String lastName, String SSN)
        {
            //using (OleDbConnection connection = new OleDbConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            //{
            //    connection.Open();
            //    OleDbCommand query = new OleDbCommand("INSERT INTO Profile (username,firstname,lastname,ssno) Values(@Username,@FirstName,@LastName,@SSNO)", connection);
            //    query.Parameters.AddWithValue("@Username", userName);
            //    query.Parameters.AddWithValue("@FirstName", firstName);
            //    query.Parameters.AddWithValue("@LastName", firstName);
            //    query.Parameters.AddWithValue("@SSNO", SSN);

            //    query.ExecuteNonQuery();
            //}

            using (SqlCeConnection connection = new SqlCeConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            {
                connection.Open();
                SqlCeCommand query = new SqlCeCommand("INSERT INTO Profile (username,firstname,lastname,ssno) Values(@Username,@FirstName,@LastName,@SSNO)", connection);
                query.Parameters.AddWithValue("@Username", userName);
                query.Parameters.AddWithValue("@FirstName", firstName);
                query.Parameters.AddWithValue("@LastName", firstName);
                query.Parameters.AddWithValue("@SSNO", SSN);

                query.ExecuteNonQuery();
            }
        }

        public String getSSN(String userName)
        {
            //using (OleDbConnection connection = new OleDbConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            //{
            //    connection.Open();
            //    OleDbCommand query = new OleDbCommand("SELECT ssno FROM [profile] WHERE username = @Username", connection);
            //    query.Parameters.AddWithValue("@Username", userName);
            //    using (OleDbDataReader reader = query.ExecuteReader())
            //    {
            //        // Call Read before accessing data.
            //        if (reader.FieldCount == 1)
            //        {
            //            while (reader.Read())
            //            {
            //                return (String)reader[0];
            //            }
            //        }
            //    }
            //    return "";
            //}

            using (SqlCeConnection connection = new SqlCeConnection(ConfigurationManager.ConnectionStrings["AppConnection"].ConnectionString))
            {
                connection.Open();
                SqlCeCommand query = new SqlCeCommand("SELECT ssno FROM [profile] WHERE username = @Username", connection);
                query.Parameters.AddWithValue("@Username", userName);
                using (SqlCeDataReader reader = query.ExecuteReader())
                {
                    // Call Read before accessing data.
                    if (reader.FieldCount == 1)
                    {
                        while (reader.Read())
                        {
                            return (String)reader[0];
                        }
                    }
                }
                return "";
            }
        }

    }
}