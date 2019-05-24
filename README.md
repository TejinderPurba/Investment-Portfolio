# Investment-Portfolio
## Course Project for CIS*2430: Object Oriented Programming F17

**************************
### Program Description
**************************
 This program implements the main and GUI for an Investment Portfolio using swing and awt package classes.
 Within the portfolio are 5 main commands, including; buy, sell
 update, get gain, and search. The portfolio must handle all these
 commands, as well as provide opportunity for the user to create
 either stocks or mutual funds, and perform tasks on each. The 
 static buy/sell commission fees for stocks is $9.99. The redemption
 fee for mutual funds are $45.00.

***********************
### User Guide
***********************
 To compile the investment portfolio program:<br/>
 run:<br/>
 &nbsp;&nbsp;&nbsp;&nbsp;javac *.java<br/> 
 &nbsp;&nbsp;&nbsp;&nbsp;java InvestmentPortfolio fileName.txt

 #### Detailed Compilation Options:

javac *.java compiles the three .java files into class files<br/>
java InvestmentPortfolio runs the class file which has the main method in it<br/> 
fileName.txt specifies the file to load and save to

 #### Program Use:
  
  Running the program launches the user into the main menu where the user can choose
  from 6 options through the GUI command menu on the top left. 
  To select a specific option, the user has a choice in:<br/>

  - For buy, user must select "Buy" from the menu
  - For sell, user must select "Sell" from the menu,
  - For update, user must select "Update" from the menu,
  - For gain, user must select "Get Gain" from the menu,
  - For search, user must select "Search" from the menu,
  - For quit, user must select "Quit" from the menu,

  ##### Buy<br/>
   The user must select "Stock" from the dropdown box to select the stock option.<br/>
   The user must select "Mutual Fund" from the dropdown box to select the mutual fund option.<br/>
   All values are then inputted in required formats, then the investment is either created or updated.<br/>
   A popup box will appear if trying to alter other investment types.

  ##### Sell<br/>
   The user must input the symbol of the investment they want to sell, along with the current price
   of the investment, and the quantity to sell.<br/>
   If the investment exists and the quantity is valid, the investment is either updated or removed.<br/>

  ##### Update<br/>
   User will go through each existing investment through the buttons, and input a new price. If user
   presses save button and price is valid, investment value is updated.

  ##### GetGain<br/>
   Selecting this option will automatically accumulate the gain of the portfolio and be displayed,
   along with displaying the individual gains.

  ##### Search<br/>
   For symbol, the user must enter either an empty string ("") or a correctly formatted value.<br/>
   For keywords, the user must enter either an empty string ("") or a correctly formatted string.<br/>
   For price, the user must enter either an empty string ("") or a correctly formatted price (or price range)
   for both the upper and lower bounds.<br/>
   If more than one field is inputted, search results will match every term. If more than one keyword
   is inputted, all keywords must match name. If price range is given, proper formatting is required.

  ##### Quit<br/>
   Program is exited.
   
   
