Online store
There are two csv files with information from client’s CRM: items.csv and customer.csv. 
[Online store was implemented using Hibernate]: https://github.com/andriistarynskyi/OnlineShopHibernate

Each file contains:
- the header with the column description 
- the body/content. 

All the values are split by delimiters (different in each file). 
LastPurchases in customers.csv can contain an array (with “”) or one element (no “”).
PhoneNumber might be empty (no value, just delimiter)
DateOfLastPurchase has a format of mm/dd/yyyy. 

Tasks:
Read files and created a collection of Java objects based on that data
Persist all the data in DB (DB should be created based on entity in csv files)

Create reports from Java:
what goods are the most popular among women
the most popular goods during a particular weekend (passed in as a param)

Alter the DB using plain SQL:
create new columns: PrimaryItem, CandidateToRemove
fetch three the most and least popular goods and mark them correspondingly in the table

Write all the marked goods (see the bullet point above) in two different files: 
primaryItems.csv with the most popular goods; 

candidateToRemove.csv - the least popular good. 

All the data should be in csv format with ; used as a delimeter.

<p align="center">
  <img width=auto height=auto src="https://github.com/andriistarynskyi/OnlineShop/blob/master/online_store_db_diagram.png">
</p>
