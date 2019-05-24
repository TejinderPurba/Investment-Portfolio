
package portfolio;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.border.EmptyBorder;

/**
 * @author Tejinder Purba
 */
public class Portfolio extends JFrame{

    public static final int V_SYMBOL = 0;
    public static final int V_NAME = 1;
    public static final int V_QUANTITY = 2;
    public static final int V_PRICE = 3;
    public static final int S_SYMBOL = 4;
    public static final int S_PRICE = 5;
    public static final int S_KEYWORDS = 6;
    private int updateIndex = 0;

    private ArrayList<Investment> investList = new ArrayList<Investment>(10);                   /*Investment List & Keyword Map Initialization*/
    private HashMap<String, List<Integer>> sKeywords = new HashMap<String, List<Integer>>();

    private JButton buyReset, buy, sellReset, sell, updateNext, updatePrev, updateSave,         /*All J-Items Declarations*/
                    searchReset, search;
    private JTextField buySymbol, buyName, buyQuantity, buyPrice, sellSymbol,
                       sellQuantity, sellPrice, updatePrice, searchSymbol, searchKey,
                       searchLPrice, searchHPrice;
    private JTextArea updateSymbol, updateName, messagesOutput, gain, individualGainOutput,
                      searchResultsOutput;
    private JComboBox buyType;
    private JMenuBar commandsBar;
    private JMenu commands;
    private JMenuItem menuBuy, menuSell, menuUpdate, menuGain, menuSearch, menuQuit;
    private JScrollPane scrollPane, gainPane, searchPane;
    private JLabel introMsg, type, symbol, name, quantity, price, totalGain, messages,
                   individualGain, searchResults, buyingAnInvestment, sellingAnInvestment, buffer,
                   updatingInvestment, gettingTotalGain, nameKeywords, lowPrice, highPrice, searchResultsText;
    private JPanel buyInputPanel, buyButtonPanel, sellInputPanel, sellButtonPanel,
                   updateInputPanel, updateButtonPanel, individualGainsPanel,
                   scrollPanel, introPanel, buyFinal, buyInputTotal,
                   sellInputTotal, sellFinal, updateInputTotal, updateFinal, gainInputPanel,
                   gainFinal, gainInputTotal, searchInputPanel, searchFinal, searchInputTotal,
                   searchButtonPanel, searchResultsPanel;

    public Portfolio(String[] commandLine){                                     /*Parse file upon program start*/
        String fileName;
        if (commandLine.length != 0) {
            fileName = commandLine[0];
        }
        else {
            fileName = "none123456abc";
        }
        parseFile(fileName);
        if (!(fileName.equalsIgnoreCase("none123456abc"))) {
            setTitle("Investment Portfolio");
        }
        else {
            setTitle("Investment Portfolio      ~ERROR IN FILE NAME - PORTFOLIO WILL NOT BE SAVED - REFER TO README~");
        }
        setSize(800, 650);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(2, 1, 10, 10));

                                                                                /*J-Items For Multiple Panels*/
        EmptyBorder msgBorder = new EmptyBorder(35,35,35,35);
        EmptyBorder titleBorder = new EmptyBorder(10,10,10,10);

        commandsBar = new JMenuBar();
        setJMenuBar(commandsBar);
        commands = new JMenu("Commands");
        commandsBar.add(commands);
        menuBuy = new JMenuItem("Buy");
        menuSell = new JMenuItem("Sell");
        menuUpdate = new JMenuItem("Update");
        menuGain = new JMenuItem("Get Gain");
        menuSearch = new JMenuItem("Search");
        menuQuit = new JMenuItem("Quit");
        commands.add(menuBuy);
        commands.add(menuSell);
        commands.add(menuUpdate);
        commands.add(menuGain);
        commands.add(menuSearch);
        commands.addSeparator();
        commands.add(menuQuit);

        type = new JLabel("                    Type");
        symbol = new JLabel("                    Symbol");
        name = new JLabel("                    Name");
        quantity = new JLabel("                    Quantity");
        price = new JLabel("                    Price");
        messages = new JLabel("  Messages");
        messages.setFont(new Font("", Font.BOLD, 14));
        messages.setBorder(titleBorder);
        buffer = new JLabel("");
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BorderLayout());
        messagesOutput = new JTextArea(10, 10);
        messagesOutput.setEditable(false);
        scrollPane = new JScrollPane(messagesOutput);
        scrollPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPanel.add(messages, BorderLayout.NORTH);
        scrollPanel.setBorder(msgBorder);

                                                                                /*J-Items Which Are Intro Exclusive*/
        introPanel = new JPanel();
        introPanel.setLayout(new GridLayout(0,1));
        introMsg = new JLabel("<html>Welcome to Investment Portfolio<br><br><br>Choose a command from"
                + " the \"Commands\" menu to buy or sell and investment, update prices for"
                + " all investments, get gain for the portfolio, search for relevant investments"
                + ", or quit the program.</html>", SwingConstants.CENTER);
        introMsg.setFont(new Font("Roboto", Font.PLAIN, 26));
        introPanel.add(introMsg);
        add(introPanel);

                                                                                /*J-Items Which Are Buy Exclusive*/
        buyInputPanel = new JPanel();
        buyFinal = new JPanel();
        buyInputTotal = new JPanel();
        buyInputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        buyInputTotal.setLayout(new BorderLayout());
        buyFinal.setLayout(new GridLayout(1, 2, 10, 10));
        buyingAnInvestment = new JLabel("          Buying An Investment");
        buyingAnInvestment.setFont(new Font("", Font.BOLD, 14));
        buyingAnInvestment.setBorder(titleBorder);
        buyType = new JComboBox();
        buyType.addItem("Stock");
        buyType.addItem("Mutual Fund");
        buyType.setSelectedIndex(0);
        buySymbol = new JTextField();
        buyName = new JTextField();
        buyQuantity = new JTextField();
        buyPrice = new JTextField();
        buyButtonPanel = new JPanel();
        buyButtonPanel.setLayout(new GridLayout(2,1,10,10));
        buyReset = new JButton("Reset");
        buy = new JButton("Buy");

                                                                                /*J-Items Which Are Sell Exclusive*/
        sellInputPanel = new JPanel();
        sellFinal = new JPanel();
        sellInputTotal = new JPanel();
        sellInputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        sellInputTotal.setLayout(new BorderLayout());
        sellFinal.setLayout(new GridLayout(1, 2, 10, 10));
        sellingAnInvestment = new JLabel("          Selling An Investment");
        sellingAnInvestment.setFont(new Font("", Font.BOLD, 14));
        sellingAnInvestment.setBorder(titleBorder);
        sellSymbol = new JTextField();
        sellQuantity = new JTextField();
        sellPrice = new JTextField();
        sellButtonPanel = new JPanel();
        sellButtonPanel.setLayout(new GridLayout(2,1,10,10));
        sellReset = new JButton("Reset");
        sell = new JButton("Sell");

                                                                                /*J-Items Which Are Update Exclusive*/
        updateInputPanel = new JPanel();
        updateFinal = new JPanel();
        updateInputTotal = new JPanel();
        updateInputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        updateInputTotal.setLayout(new BorderLayout());
        updateFinal.setLayout(new GridLayout(1, 2, 10, 10));
        updatingInvestment = new JLabel("          Updating Investment");
        updatingInvestment.setFont(new Font("", Font.BOLD, 14));
        updatingInvestment.setBorder(titleBorder);
        updateSymbol = new JTextArea();
        updatePrice = new JTextField();
        updateName = new JTextArea();
        updateButtonPanel = new JPanel();
        updateButtonPanel.setLayout(new GridLayout(3,1,10,10));
        updateNext = new JButton("Next");
        updatePrev = new JButton("Prev");
        updateSave = new JButton("Save");
        updateSymbol.setEditable(false);
        updateName.setEditable(false);

                                                                                /*J-Items Which Are Gain Exclusive*/
        gainInputPanel = new JPanel();
        gainInputTotal = new JPanel();
        gainFinal = new JPanel();
        gainInputPanel.setLayout(new GridLayout(1, 2, 10, 10));
        gainInputTotal.setLayout(new BorderLayout());
        gainFinal.setLayout(new GridLayout(1, 2, 10, 10));
        gettingTotalGain = new JLabel("          Getting Total Gain");
        gettingTotalGain.setFont(new Font("", Font.BOLD, 14));
        gettingTotalGain.setBorder(titleBorder);
        totalGain = new JLabel("                    Total Gain");
        individualGain = new JLabel("  Individual Gains");
        individualGain.setFont(new Font("", Font.BOLD, 14));
        individualGain.setBorder(titleBorder);
        individualGainsPanel = new JPanel();
        individualGainsPanel.setLayout(new BorderLayout());
        individualGainOutput = new JTextArea(10, 10);
        individualGainOutput.setEditable(false);
        gainPane = new JScrollPane(individualGainOutput);
        individualGainsPanel.add(gainPane, BorderLayout.CENTER);
        individualGainsPanel.add(individualGain, BorderLayout.NORTH);
        individualGainsPanel.setBorder(msgBorder);
        gain = new JTextArea();
        gain.setEditable(false);

                                                                                /*J-Items Which Are Search Exclusive*/
        searchInputPanel = new JPanel();
        searchFinal = new JPanel();
        searchInputTotal = new JPanel();
        searchInputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        searchInputTotal.setLayout(new BorderLayout());
        searchFinal.setLayout(new GridLayout(1, 2, 10, 10));
        searchResults = new JLabel("          Searching Investments");
        searchResultsText = new JLabel("  Search Results");
        searchResultsText.setFont(new Font("", Font.BOLD, 14));
        searchResultsText.setBorder(titleBorder);
        searchResults.setFont(new Font("", Font.BOLD, 14));
        searchResults.setBorder(titleBorder);
        searchSymbol = new JTextField();
        searchKey = new JTextField();
        searchLPrice = new JTextField();
        searchHPrice = new JTextField();
        searchButtonPanel = new JPanel();
        searchButtonPanel.setLayout(new GridLayout(2,1,10,10));
        searchReset = new JButton("Reset");
        search = new JButton("Search");
        searchResultsPanel = new JPanel();
        searchResultsPanel.setLayout(new BorderLayout());
        searchResultsOutput = new JTextArea(10,10);
        searchResultsOutput.setEditable(false);
        searchPane = new JScrollPane(searchResultsOutput);
        searchResultsPanel.add(searchPane, BorderLayout.CENTER);
        searchResultsPanel.add(searchResultsText, BorderLayout.NORTH);
        searchResultsPanel.setBorder(msgBorder);
        nameKeywords = new JLabel("                    Name/Keywords");
        lowPrice = new JLabel("                    Low Price");
        highPrice = new JLabel("                    High Price");

        menuBuy.addActionListener(new ActionListener(){                         /*Buy command from dropdown menu*/
            @Override
            public void actionPerformed(ActionEvent a) {
                removePanels();
                revalidate();
                repaint();
                setLayout(new GridLayout(2, 1, 10, 10));
                buyInputPanel.add(type);
                buyInputPanel.add(buyType);
                buyInputPanel.add(symbol);
                buyInputPanel.add(buySymbol);
                buyInputPanel.add(name);
                buyInputPanel.add(buyName);
                buyInputPanel.add(quantity);
                buyInputPanel.add(buyQuantity);
                buyInputPanel.add(price);
                buyInputPanel.add(buyPrice);
                buyButtonPanel.add(buyReset);
                buyButtonPanel.add(buy);
                buyInputTotal.add(buyingAnInvestment, BorderLayout.NORTH);
                buyInputTotal.add(buyInputPanel, BorderLayout.CENTER);
                buyFinal.add(buyInputTotal);
                buyFinal.add(buyButtonPanel);
                add(buyFinal);
                add(scrollPanel);
                messagesOutput.setText("");
                buySymbol.setText("");
                buyName.setText("");
                buyQuantity.setText("");
                buyPrice.setText("");
                revalidate();
                repaint();
            }
        });

        buy.addActionListener(new ActionListener(){                             /*Buy button response*/
            @Override
            public void actionPerformed(ActionEvent a) {
                String warning = "";
                if (!(validateFormat(buySymbol.getText(), V_SYMBOL))) {
                    warning += "Error: Incorrect format for Symbol. (Empty strings, spaces, & punctuation not allowed! Refer to README)\n\n";
                }
                if (!(validateFormat(buyName.getText(), V_NAME))) {
                    warning += "Error: Incorrect format for Name. (Empty strings, dashes, & commas not allowed! Refer to README)\n\n";
                }
                if (!(validateFormat(buyQuantity.getText(), V_QUANTITY))) {
                    warning += "Error: Incorrect format for Quantity. (Must be greater than 0!)\n\n";
                }
                if (!(validateFormat(buyPrice.getText(), V_PRICE))) {
                    warning += "Error: Incorrect format for Price. (Must be greater than 0!)\n\n";
                }
                if (warning.equals("")) {
                    int quantity = Integer.parseInt(buyQuantity.getText());
                    double price = Double.parseDouble(buyPrice.getText());
                    messagesOutput.setText(buy(buyType.getSelectedItem().toString(), buySymbol.getText(), buyName.getText(), quantity, price));
                }
                else {
                    messagesOutput.setText(warning);
                }
            }
        });

        buyReset.addActionListener(new ActionListener(){                        /*Buy panel reset button response*/
            @Override
            public void actionPerformed(ActionEvent a) {
                 buySymbol.setText("");
                 buyQuantity.setText("");
                 buyName.setText("");
                 buyPrice.setText("");
                 buyType.setSelectedIndex(0);
            }
        });

        menuSell.addActionListener(new ActionListener(){                        /*Sell command from the dropdown menu*/
            @Override
            public void actionPerformed(ActionEvent a) {
                removePanels();
                revalidate();
                repaint();
                setLayout(new GridLayout(2, 1, 10, 10));
                sellInputPanel.add(symbol);
                sellInputPanel.add(sellSymbol);
                sellInputPanel.add(quantity);
                sellInputPanel.add(sellQuantity);
                sellInputPanel.add(price);
                sellInputPanel.add(sellPrice);
                sellButtonPanel.add(sellReset);
                sellButtonPanel.add(sell);
                sellInputTotal.add(sellingAnInvestment, BorderLayout.NORTH);
                sellInputTotal.add(sellInputPanel, BorderLayout.CENTER);
                sellFinal.add(sellInputTotal);
                sellFinal.add(sellButtonPanel);
                add(sellFinal);
                add(scrollPanel);
                messagesOutput.setText("");
                sellSymbol.setText("");
                sellQuantity.setText("");
                sellPrice.setText("");
                revalidate();
                repaint();
            }
        });

        sell.addActionListener(new ActionListener(){                            /*Sell button response*/
            @Override
            public void actionPerformed(ActionEvent a) {
                String warning = "";
                if (!(validateFormat(sellSymbol.getText(), V_SYMBOL))) {
                    warning += "Error: Incorrect format for Symbol. (Empty strings, spaces, & punctuation not allowed! Refer to README)\n\n";
                }
                if (!(validateFormat(sellQuantity.getText(), V_QUANTITY))) {
                    warning += "Error: Incorrect format for Quantity. (Must be greater than 0!)\n\n";
                }
                if (!(validateFormat(sellPrice.getText(), V_PRICE))) {
                    warning += "Error: Incorrect format for Price. (Must be greater than 0!)\n\n";
                }
                if (warning.equals("")) {
                    int quantity = Integer.parseInt(sellQuantity.getText());
                    double price = Double.parseDouble(sellPrice.getText());
                    messagesOutput.setText(sell(sellSymbol.getText(), quantity, price));
                }
                else {
                    messagesOutput.setText(warning);
                }
            }
        });

        sellReset.addActionListener(new ActionListener(){                       /*Reset button from sell panel response*/
            @Override
            public void actionPerformed(ActionEvent a) {
                 sellSymbol.setText("");
                 sellQuantity.setText("");
                 sellPrice.setText("");
            }
        });

        menuUpdate.addActionListener(new ActionListener(){                      /*Update command from dropdown menu*/
            @Override
            public void actionPerformed(ActionEvent a) {
                removePanels();
                revalidate();
                repaint();
                setLayout(new GridLayout(2, 1, 10, 10));
                updateInputPanel.add(symbol);
                updateInputPanel.add(updateSymbol);
                updateInputPanel.add(name);
                updateInputPanel.add(updateName);
                updateInputPanel.add(price);
                updateInputPanel.add(updatePrice);
                updateButtonPanel.add(updatePrev);
                updateButtonPanel.add(updateNext);
                updateButtonPanel.add(updateSave);
                updateInputTotal.add(updatingInvestment, BorderLayout.NORTH);
                updateInputTotal.add(updateInputPanel, BorderLayout.CENTER);
                updateFinal.add(updateInputTotal);
                updateFinal.add(updateButtonPanel);
                add(updateFinal);
                add(scrollPanel);
                messagesOutput.setText("");
                updatePrice.setText("");
                revalidate();
                repaint();

                if (investList.size() == 0) {
                    messagesOutput.setText("\nNo Stocks or Mutual Funds!");
                }
                else {
                    updateSymbol.setText(String.format("\n\n%s", investList.get(0).getSymbol()));
                    updateName.setText(String.format("\n\n%s", investList.get(0).getName()));
                    messagesOutput.setText(String.format("\nCurrent Price: $%.2f", investList.get(0).getPrice()));
                    updateIndex = 0;
                }
            }
        });

        updateSave.addActionListener(new ActionListener(){                      /*Save button response from update panel*/
            @Override
            public void actionPerformed(ActionEvent a) {
                String warning = "";
                if (investList.size() != 0) {
                    if (!(validateFormat(updatePrice.getText(), V_PRICE))) {
                        warning += "Error: Incorrect format for Price. (Must be greater than 0!)\n\n";
                    }
                    if (warning.equals("")) {
                        double price = Double.parseDouble(updatePrice.getText());
                        messagesOutput.setText(update(price));
                    }
                    else {
                        messagesOutput.setText(warning);
                    }
                }
            }
        });

        updatePrev.addActionListener(new ActionListener(){                      /*Prev button response from update panel*/
            @Override
            public void actionPerformed(ActionEvent a) {
                if (updateIndex > 0) {
                    --updateIndex;
                    updateSymbol.setText(String.format("\n\n%s", investList.get(updateIndex).getSymbol()));
                    updateName.setText(String.format("\n\n%s", investList.get(updateIndex).getName()));
                    messagesOutput.setText(String.format("\nCurrent Price: $%.2f", investList.get(updateIndex).getPrice()));
                }
            }
        });

        updateNext.addActionListener(new ActionListener(){                      /*Next button response from update panel*/
            @Override
            public void actionPerformed(ActionEvent a) {
                if (updateIndex < investList.size()-1) {
                    ++updateIndex;
                    updateSymbol.setText(String.format("\n\n%s", investList.get(updateIndex).getSymbol()));
                    updateName.setText(String.format("\n\n%s", investList.get(updateIndex).getName()));
                    messagesOutput.setText(String.format("\nCurrent Price: $%.2f", investList.get(updateIndex).getPrice()));
                }
            }
        });

        menuGain.addActionListener(new ActionListener(){                        /*Get Gain command from dropdown menu*/
            @Override
            public void actionPerformed(ActionEvent a) {
                removePanels();
                revalidate();
                repaint();
                setLayout(new GridLayout(2, 1, 10, 10));
                gainInputPanel.add(totalGain);
                gainInputPanel.add(gain);
                gainInputTotal.add(gettingTotalGain, BorderLayout.NORTH);
                gainInputTotal.add(gainInputPanel, BorderLayout.CENTER);
                gainFinal.add(gainInputTotal);
                gainFinal.add(buffer);
                add(gainFinal);
                add(individualGainsPanel);
                revalidate();
                repaint();
                if (investList.size() == 0) {
                    individualGainOutput.setText("\nNo Stocks or Mutual Funds!");
                }
                else {
                    individualGainOutput.setText(getGainString());
                }
                double num = getGain();
                String output = String.format("\n\n\n\n\n\n\n\n$%.2f", num);
                gain.setText(output);
            }
        });

        menuSearch.addActionListener(new ActionListener(){                      /*Search command from dropdown menu*/
            @Override
            public void actionPerformed(ActionEvent a) {
                removePanels();
                revalidate();
                repaint();
                setLayout(new GridLayout(2, 1, 10, 10));
                searchInputPanel.add(symbol);
                searchInputPanel.add(searchSymbol);
                searchInputPanel.add(nameKeywords);
                searchInputPanel.add(searchKey);
                searchInputPanel.add(lowPrice);
                searchInputPanel.add(searchLPrice);
                searchInputPanel.add(highPrice);
                searchInputPanel.add(searchHPrice);
                searchInputTotal.add(searchResults, BorderLayout.NORTH);
                searchInputTotal.add(searchInputPanel, BorderLayout.CENTER);
                searchButtonPanel.add(searchReset);
                searchButtonPanel.add(search);
                searchFinal.add(searchInputTotal);
                searchFinal.add(searchButtonPanel);
                add(searchFinal);
                add(searchResultsPanel);
                searchResultsOutput.setText("");
                searchSymbol.setText("");
                searchKey.setText("");
                searchLPrice.setText("");
                searchHPrice.setText("");
                revalidate();
                repaint();
            }
        });

        search.addActionListener(new ActionListener(){                          /*Search button response from search panel*/
            @Override
            public void actionPerformed(ActionEvent a) {
                String warning = "";
                if (!(validateFormatSearch(searchSymbol.getText(), S_SYMBOL))) {
                    warning += "Error: Incorrect format for Symbol. (Empty strings, spaces, & punctuation not allowed! Refer to README)\n\n";
                }
                if (!(validateFormatSearch(searchLPrice.getText(), S_PRICE))) {
                    warning += "Error: Incorrect format for Low Price. (Must be greater than 0 or empty!)\n\n";
                }
                if (!(validateFormatSearch(searchHPrice.getText(), S_PRICE))) {
                    warning += "Error: Incorrect format for High Price. (Must be greater than 0 or empty!)\n\n";
                }
                if (!(validateFormatSearch(searchKey.getText(), S_KEYWORDS))) {
                    warning += "Error: Incorrect format for Keywords. (Cannot contain only spaces!)\n\n";
                }
                if (warning.equals("")) {
                    searchResultsOutput.setText(search(searchSymbol.getText(), searchKey.getText(), searchHPrice.getText(), searchLPrice.getText()));
                }
                else {
                    searchResultsOutput.setText(warning);
                }
            }
        });

        searchReset.addActionListener(new ActionListener(){                     /*Reset button response from search panel*/
            @Override
            public void actionPerformed(ActionEvent a) {
                searchSymbol.setText("");
                searchKey.setText("");
                searchLPrice.setText("");
                searchHPrice.setText("");
            }
        });

        menuQuit.addActionListener(new ActionListener(){                        /*Quit command from dropdown menu*/
            @Override
            public void actionPerformed(ActionEvent a) {
                saveFile(fileName);
                System.exit(0);
            }
        });

        WindowListener exitListener = new WindowAdapter() {                     /*'X' button listener for exiting*/
            @Override
            public void windowClosing(WindowEvent e) {
                saveFile(fileName);
                System.exit(0);
            }
        };
        addWindowListener(exitListener);

    }

    /**
     * Main method of the program
     * @param args string array for command line arguments
     */
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio(args);              /*Initialize new portfolio object*/
        portfolio.setVisible(true);

    }

    /**
     * Buy method asks user to specify investment type to buy, gathers all necessary input,
     * checks if investments exists already, if so, update existing investment, else create a new
     * object of class stock or mutual fund
     * @param investType string for investment type
     * @param symbol string for the symbol of investment
     * @param name sting for the name of investment
     * @param quantity integer for quantity of investment
     * @param price double for price of investment
     * @return string with output message
     */
    public String buy(String investType, String symbol, String name, int quantity, double price) {
        Scanner s = new Scanner(System.in);
        boolean userCheck = false;
        double bookValue = -1.0;
        String userChoice = null;
        int unique = -1;
        int unique2 = -1;
        String exists = null;

        userChoice = investType;
        if (userChoice.equalsIgnoreCase("Stock")) {
            userChoice = "1";
        }
        else if (userChoice.equalsIgnoreCase("Mutual Fund")) {
            userChoice = "2";
        }

        if (userChoice.equals("1")) {
            bookValue = (quantity * price) + 9.99;
            Stock temp = null;
            MutualFund temp2 = null;
            try {
                temp = new Stock(symbol, name, quantity, price, bookValue);
                temp2 = new MutualFund(symbol, name, quantity, price, bookValue);
            }
            catch (EmptyFieldException | QuantityFieldException | PriceFieldException | BookvalueFieldException e) {
                return e.getMessage();
            }
            unique = checkStockUnique(temp);                                    /*Checking if investment exists in both lists*/
            unique2 = checkFundUnique(temp2);
            if (unique == -1 && unique2 == -1) {                                /*If does not exist, create new object*/
                if (temp.getName().contains(" ")) {
                    for (String retval: temp.getName().toLowerCase().split(" ")) { /*If name has multiple keywords, split and add/update to hashmap*/
                        List<Integer> indices = new ArrayList<Integer>();
                        List<Integer> indices2;
                        if (sKeywords.containsKey(retval.toLowerCase())) {
                            indices2 = sKeywords.get(retval.toLowerCase());
                            indices2.add(investList.size());
                            sKeywords.put(retval.toLowerCase(), indices2);
                        }
                        else {
                            indices.add(investList.size());
                            sKeywords.put(retval.toLowerCase(), indices);
                        }
                    }
                }
                else {
                    List<Integer> indices = new ArrayList<Integer>();               /*If name only has one keyword, add/update to hashmap*/
                    List<Integer> indices2;
                    if (sKeywords.containsKey(temp.getName().toLowerCase())) {
                        indices2 = sKeywords.get(temp.getName().toLowerCase());
                        indices2.add(investList.size());
                        sKeywords.put(temp.getName().toLowerCase(), indices2);
                    }
                    else {
                        indices.add(investList.size());
                        sKeywords.put(temp.getName().toLowerCase(), indices);
                    }
                }

                investList.add(temp);
            }
            else {                                                              /*Else whichever investment already exists, update it*/
                if (unique2 != -1) {
                    userCheck = false;
                    while (userCheck == false) {
                        int reply = showConfirmDialog(null, "Investment already exists as a Mutual Fund! Do you wish to update the Mutual Fund with the current values?", "Warning: Investment Already Exists", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            exists = "y";
                        }
                        else {
                            exists = "n";
                        }

                        if (exists != null && (exists.replace(" ", "").equalsIgnoreCase("y") || exists.replace(" ", "").equalsIgnoreCase("yes") || exists.replace(" ", "").equalsIgnoreCase("yup"))) {
                            bookValue = this.investList.get(unique2).getBookValue() + (quantity * price);
                            try {
                                this.investList.get(unique2).setQuantity(quantity + this.investList.get(unique2).getQuantity());
                                this.investList.get(unique2).setBookValue(bookValue);
                                this.investList.get(unique2).setPrice(price);
                            }
                            catch (QuantityFieldException | BookvalueFieldException | PriceFieldException e) {
                                return e.getMessage();
                            }
                            return "\nUpdated!\n";
                        }
                        else if (exists != null && (exists.replace(" ", "").equalsIgnoreCase("n") || exists.replace(" ", "").equalsIgnoreCase("no") || exists.replace(" ", "").equalsIgnoreCase("nope"))) {
                            return "\nMutual Fund not updated...";
                        }
                    }
                }
                else if (unique != -1) {
                    bookValue = this.investList.get(unique).getBookValue() + ((quantity * price) + 9.99);
                    try {
                        this.investList.get(unique).setQuantity(quantity + this.investList.get(unique).getQuantity());
                        this.investList.get(unique).setBookValue(bookValue);
                        this.investList.get(unique).setPrice(price);
                    }
                    catch (QuantityFieldException | BookvalueFieldException | PriceFieldException e) {
                        return e.getMessage();
                    }
                    return "\nStock already exists! Updating quantity, price, and bookvalue...";
                }
            }
        }
        else if (userChoice.equals("2")) {
            bookValue = quantity * price;
            MutualFund temp = null;
            Stock temp2 = null;
            try {
                temp = new MutualFund(symbol, name, quantity, price, bookValue);
                temp2 = new Stock(symbol, name, quantity, price, bookValue);
            }
            catch (EmptyFieldException | QuantityFieldException | PriceFieldException | BookvalueFieldException e) {
                return e.getMessage();
            }
            unique = checkFundUnique(temp);                                     /*Checking if investment exists in both lists*/
            unique2 = checkStockUnique(temp2);
            if (unique == -1 && unique2 == -1) {                                /*If does not exist, create new object*/
                if (temp.getName().contains(" ")) {
                    for (String retval: temp.getName().toLowerCase().split(" ")) {  /*If name has multiple keywords, split and add/update to hashmap*/
                        List<Integer> indices = new ArrayList<Integer>();
                        List<Integer> indices2;
                        if (sKeywords.containsKey(retval.toLowerCase())) {
                            indices2 = sKeywords.get(retval.toLowerCase());
                            indices2.add(investList.size());
                            sKeywords.put(retval.toLowerCase(), indices2);
                        }
                        else {
                            indices.add(investList.size());
                            sKeywords.put(retval.toLowerCase(), indices);
                        }
                    }
                }
                else {
                    List<Integer> indices = new ArrayList<Integer>();               /*If name only has one keyword, add/update to hashmap*/
                    List<Integer> indices2;
                    if (sKeywords.containsKey(temp.getName().toLowerCase())) {
                        indices2 = sKeywords.get(temp.getName().toLowerCase());
                        indices2.add(investList.size());
                        sKeywords.put(temp.getName().toLowerCase(), indices2);
                    }
                    else {
                        indices.add(investList.size());
                        sKeywords.put(temp.getName().toLowerCase(), indices);
                    }
                }
                investList.add(temp);
            }
            else {                                                              /*Else whichever investment already exists, update it*/
                if (unique != -1) {
                    bookValue = this.investList.get(unique).getBookValue() + (quantity * price);
                    try {
                        this.investList.get(unique).setQuantity(quantity + this.investList.get(unique).getQuantity());
                        this.investList.get(unique).setBookValue(bookValue);
                        this.investList.get(unique).setPrice(price);
                    }
                    catch (QuantityFieldException | BookvalueFieldException | PriceFieldException e) {
                        return e.getMessage();
                    }
                    return "\nMutual Fund already exists! Updating quantity, price, and bookvalue...";
                }
                else if (unique2 != -1) {
                    userCheck = false;
                    while (userCheck == false) {
                        int reply = showConfirmDialog(null, "Investment already exists as a Stock! Do you wish to update the Stock with the current values?", "Warning: Investment Already Exists", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            exists = "y";
                        }
                        else {
                            exists = "n";
                        }

                        if (exists != null && (exists.replace(" ", "").equalsIgnoreCase("y") || exists.replace(" ", "").equalsIgnoreCase("yes") || exists.replace(" ", "").equalsIgnoreCase("yup"))) {
                            bookValue = this.investList.get(unique2).getBookValue() + (quantity * price + 9.99);
                            try {
                                this.investList.get(unique2).setQuantity(quantity + this.investList.get(unique2).getQuantity());
                                this.investList.get(unique2).setBookValue(bookValue);
                                this.investList.get(unique2).setPrice(price);
                            }
                            catch (QuantityFieldException | BookvalueFieldException | PriceFieldException e) {
                                return e.getMessage();
                            }
                            return "\nUpdated!\n";
                        }
                        else if (exists != null && (exists.replace(" ", "").equalsIgnoreCase("n") || exists.replace(" ", "").equalsIgnoreCase("no") || exists.replace(" ", "").equalsIgnoreCase("nope"))) {
                            return "\nStock not updated...";
                        }
                    }
                }
            }
        }
        return "\nSuccessfully Added!";
    }

    /**
     * Sell method to gather investment information from user, and attempt to sell
     * if the investment exists.
     * If quantity is 0 after selling, investment is deleted, else quantity, price
     * and book value are updated.
     * @param symbol string for symbol of investment
     * @param quantity integer for quantity of investment
     * @param price double for price of investment
     * @return string with output message
     */
    public String sell(String symbol, int quantity, double price) {
        String name = "test";
        double bookValue = 1.0;
        int unique = -1;
        int unique2 = -1;
        int newAmount = -1;
        {
            MutualFund temp = null;
            Stock temp2 = null;
            try {
                temp = new MutualFund(symbol, name, quantity, price, bookValue);
                temp2 = new Stock(symbol, name, quantity, price, bookValue);
            }
            catch (EmptyFieldException | QuantityFieldException | PriceFieldException | BookvalueFieldException e) {
                return e.getMessage();
            }
            unique = checkFundUnique(temp);                                         /*Check to see if investment exists*/
            unique2 = checkStockUnique(temp2);
            if (unique == -1 && unique2 == -1) {
                return "\nError: Investment does not exist!\n";
            }
        }

        if (unique != -1) {
            if (quantity < 1 || quantity > this.investList.get(unique).getQuantity()) {
                return "\nError: Invalid quantity! Available quantity is "+this.investList.get(unique).getQuantity()+"\n";
            }
        }
        else if (unique2 != -1) {
            if (quantity < 1 || quantity > this.investList.get(unique2).getQuantity()) {
                return "\nError: Invalid quantity! Available quantity is "+this.investList.get(unique2).getQuantity()+"\n";
            }
        }

        /*If the investment already exists as a mutual fund*/
        if (unique != -1) {
            if ((this.investList.get(unique).getQuantity() - quantity) == 0 ) {                        /*If all funds are sold, remove investment*/
                try {
                    this.investList.get(unique).setPrice(price);
                }
                catch (PriceFieldException e) {
                    return e.getMessage();
                }
                double amount = (this.investList.get(unique).getPrice() * quantity) - 45;
                MutualFund temp = (MutualFund)investList.get(unique);
                if (temp.getName().contains(" ")) {
                    for (String retval: temp.getName().toLowerCase().split(" ")) {              /*Remove index value for each keyword in name*/
                        List<Integer> indices2;
                        if (sKeywords.containsKey(retval.toLowerCase())) {
                            indices2 = sKeywords.get(retval.toLowerCase());
                            for (int i = 0; i < indices2.size(); i++) {
                                if (indices2.get(i) == unique) {
                                    indices2.remove(i);
                                }
                            }
                            if (indices2.size() > 0) {
                                sKeywords.put(retval.toLowerCase(), indices2);
                            }
                            else {
                                sKeywords.remove(retval.toLowerCase());
                            }
                        }
                    }
                    for (Map.Entry<String, List<Integer>> entry : sKeywords.entrySet()) {    /*Iterate hashmap and update each index to new values*/
                        List<Integer> indices2 = entry.getValue();
                        String key = entry.getKey();
                        for (int i = 0; i < indices2.size(); i++) {
                            if (indices2.get(i) > unique && indices2.get(i) > 0) {
                                indices2.set(i, indices2.get(i)-1);
                            }
                            if (indices2.size() > 0) {
                                sKeywords.put(key.toLowerCase(), indices2);
                            }
                        }
                    }
                }
                else {
                    List<Integer> indices2;
                    if (sKeywords.containsKey(temp.getName().toLowerCase())) {          /*Name only contains one keyword, remove index*/
                        indices2 = sKeywords.get(temp.getName().toLowerCase());
                        for (int i = 0; i < indices2.size(); i++) {
                            if (indices2.get(i) == unique) {
                                indices2.remove(i);
                            }
                        }
                        if (indices2.size() > 0) {
                            sKeywords.put(temp.getName().toLowerCase(), indices2);
                        }
                        else {
                            sKeywords.remove(temp.getName().toLowerCase());
                        }
                        for (Map.Entry<String, List<Integer>> entry : sKeywords.entrySet()) {   /*Update index values in hashmap*/
                            indices2 = entry.getValue();
                            String key = entry.getKey();
                            for (int i = 0; i < indices2.size(); i++) {
                                if (indices2.get(i) > unique && indices2.get(i) > 0) {
                                    indices2.set(i, indices2.get(i)-1);
                                }
                            }
                        }
                    }
                }
                this.investList.remove(unique);
                return String.format("\nAll Shares Sold! Payment Received: $%.2f\n\n", amount);
            }
            else {                                                                                          /*If not all funds are sold, update all values*/
                try {
                    this.investList.get(unique).setPrice(price);
                }
                catch (PriceFieldException e) {
                    return e.getMessage();
                }
                double amount = (this.investList.get(unique).getPrice() * quantity) - 45;
                newAmount = this.investList.get(unique).getQuantity() - quantity;
                bookValue = this.investList.get(unique).getBookValue() * ((double)newAmount / ((double)newAmount + (double)quantity));
                try {
                    this.investList.get(unique).setQuantity(newAmount);
                    this.investList.get(unique).setBookValue(bookValue);
                }
                catch (QuantityFieldException | BookvalueFieldException e) {
                    e.getMessage();
                }
                return String.format("\n%d Shares Sold! Payment Received: $%.2f\n\n", quantity, amount);
            }
        }

        /*Else if the investment already exists as a stock*/
        else if (unique2 != -1) {
            if ((this.investList.get(unique2).getQuantity() - quantity) == 0 ) {                             /*If all funds are sold, remove investment*/
                try {
                    this.investList.get(unique2).setPrice(price);
                }
                catch (PriceFieldException e) {
                    return e.getMessage();
                }
                double amount = (this.investList.get(unique2).getPrice() * quantity) - 9.99;
                Stock temp = (Stock)investList.get(unique2);
                if (temp.getName().contains(" ")) {
                    for (String retval: temp.getName().toLowerCase().split(" ")) {
                        List<Integer> indices2;
                        if (sKeywords.containsKey(retval.toLowerCase())) {
                            indices2 = sKeywords.get(retval.toLowerCase());
                            for (int i = 0; i < indices2.size(); i++) {
                                if (indices2.get(i) == unique2) {                       /*Remove index value for each keyword in name*/
                                    indices2.remove(i);
                                }
                            }
                            if (indices2.size() > 0) {
                                sKeywords.put(retval.toLowerCase(), indices2);
                            }
                            else {
                                sKeywords.remove(retval.toLowerCase());
                            }
                        }
                    }
                    for (Map.Entry<String, List<Integer>> entry : sKeywords.entrySet()) {      /*Iterate through hashmap and update index values*/
                        List<Integer> indices2 = entry.getValue();
                        String key = entry.getKey();
                        for (int i = 0; i < indices2.size(); i++) {
                            if (indices2.get(i) > unique2 && indices2.get(i) > 0) {
                                indices2.set(i, indices2.get(i)-1);
                            }
                            if (indices2.size() > 0) {
                                sKeywords.put(key.toLowerCase(), indices2);
                            }
                        }
                    }
                }
                else {
                    List<Integer> indices2;
                    if (sKeywords.containsKey(temp.getName().toLowerCase())) {                  /*Only one keyword, remove index*/
                        indices2 = sKeywords.get(temp.getName().toLowerCase());
                        for (int i = 0; i < indices2.size(); i++) {
                            if (indices2.get(i) == unique2) {
                                indices2.remove(i);
                            }
                        }
                        if (indices2.size() > 0) {
                            sKeywords.put(temp.getName().toLowerCase(), indices2);
                        }
                        else {
                            sKeywords.remove(temp.getName().toLowerCase());
                        }
                        for (Map.Entry<String, List<Integer>> entry : sKeywords.entrySet()) {           /*Iterate through hashmap and update values*/
                            indices2 = entry.getValue();
                            String key = entry.getKey();
                            for (int i = 0; i < indices2.size(); i++) {
                                if (indices2.get(i) > unique2 && indices2.get(i) > 0) {
                                    indices2.set(i, indices2.get(i)-1);
                                }
                            }
                        }
                    }
                }
                this.investList.remove(unique2);
                return String.format("\nAll Shares Sold! Payment Receieved: $%.2f\n\n",amount);
            }
            else {                                                                                          /*If not all funds are sold, update all values*/
                try {
                    this.investList.get(unique2).setPrice(price);
                }
                catch (PriceFieldException e) {
                    return e.getMessage();
                }
                double amount = (this.investList.get(unique2).getPrice() * quantity) - 9.99;
                newAmount = this.investList.get(unique2).getQuantity() - quantity;
                bookValue = this.investList.get(unique2).getBookValue() * ((double)newAmount / ((double)newAmount + (double)quantity));
                try {
                    this.investList.get(unique2).setQuantity(newAmount);
                    this.investList.get(unique2).setBookValue(bookValue);
                }
                catch (QuantityFieldException | BookvalueFieldException e) {
                    return e.getMessage();
                }
                return String.format("\n%d Shares Sold! Payment Receieved: $%.2f\n\n",quantity, amount);
            }
        }
        return "Investment sold successfully!";
    }

    /**
     * Update method to go through each investment and update the price based
     * on user input
     * @param price double with update price for investment
     * @return string with output message
     */
    public String update(double price) {
        String output = "";
        try {
            this.investList.get(updateIndex).setPrice(price);
        }
        catch (PriceFieldException e) {
            return e.getMessage();
        }
        output = String.format("Updated! Full Content Below...\n\nSymbol: %s\nName: %s\nQuantity: %d\nPrice: $%.2f\nBookValue: $%.2f",
                this.investList.get(updateIndex).getSymbol(), this.investList.get(updateIndex).getName(),
                this.investList.get(updateIndex).getQuantity(), this.investList.get(updateIndex).getPrice(),
                this.investList.get(updateIndex).getBookValue());
        return output;
    }

    /**
     * GetGain method iterates through both lists and calculates gain on each investment,
     * displaying total gain in whole portfolio
     * @return gain value calculated
     */
    public double getGain() {
        double gain = 0.0;

        /*Iterate through investments list and accumulate gain*/
        for (int i = 0; i < this.investList.size(); i++) {
            gain = gain + this.investList.get(i).calcGain(this.investList.get(i).getQuantity(), this.investList.get(i).getPrice(), this.investList.get(i).getBookValue());
        }
        return gain;
    }

    /**
     * GetGain method iterates through both lists and calculates gain on each investment,
     * displaying total gain in whole portfolio
     * @return string with individual gains of investments
     */
    public String getGainString() {
        double gain = 0.0;
        String output = "";

        /*Iterate through investments list and accumulate gain*/
        for (int i = 0; i < this.investList.size(); i++) {
            gain = this.investList.get(i).calcGain(this.investList.get(i).getQuantity(), this.investList.get(i).getPrice(), this.investList.get(i).getBookValue());
            output += String.format("\nSymbol: "+this.investList.get(i).getSymbol()+"\nName: "+ this.investList.get(i).getName() + "\nGain: $%.2f\n", gain);
        }
        return output;
    }

    /**
     * Search method to get symbol, keywords, and price range inputs from user,
     * and filter through all investments, returning matching criteria,
     * simplified through copying all investments to new lists, and filtering and removing
     * each non-fitting investment.
     * @param symbol string for symbol to search
     * @param keywordsStore string for keywords to search
     * @param priceH string for upper bound of price to search
     * @param priceL string for lower bound of price to search
     * @return string with search results
     */
    public String search(String symbol, String keywordsStore, String priceH, String priceL) {

        Scanner s = new Scanner(System.in);
        String delimeters = "[ ]+";
        boolean spaceCheck = false;
        String keyword = "";
        String price = "";
        String[] keywords = new String[100];

        /*While loop to gather keywords from user. If multiple keywords, split
        into a string array, else store in a single string variable*/
        if (keywordsStore != null) {
            if (keywordsStore.contains(" ")) {
                if (keywordsStore.replace(" ", "").length() != 0) {
                    spaceCheck = true;
                    keywords = keywordsStore.split(delimeters);
                    if (keywords[0].equals("")) {
                        for(int i=0; i < keywords.length-1; i++){       /*If first element is empty from delimiters, shift down elements*/
                            keywords[i] = keywords[i+1];
                        }
                    }
                }
            }
            else {
                keywords[0] = "";
                keyword = keywordsStore;
                spaceCheck = false;
            }
        }

        /*Store both stocks and mutual funds into new arrayLists. Go through each
        search field, then use the search terms to remove the incorrect ones,
        leaving with ones that fit with all fields*/

        ArrayList<Investment> storeInvestList = new ArrayList<Investment>(10);
        storeInvestList.addAll(investList);

        /*If keywords input exists, search through each storage list and remove
        investments that do not match*/
        if (keyword.length() != 0 || !(keywords[0].equals(""))) {
            /*If more than one keyword was entered, spaceCheck will be true,
            and each keyword will have to be found*/
            if (spaceCheck == true) {
                List<Integer> indices = new ArrayList<Integer>();
                List<Integer> indices2 = new ArrayList<Integer>();

                if (sKeywords.containsKey(keywords[0].toLowerCase())) {             /*Multiple keywords, check if they exist in hashmap*/
                    indices = sKeywords.get(keywords[0].toLowerCase());
                }
                else {
                    indices = null;
                }

                if (sKeywords.containsKey(keywords[1].toLowerCase())) {
                    indices2 = sKeywords.get(keywords[1].toLowerCase());
                }
                else {
                    indices2 = null;
                }

                if (indices == null || indices2 == null) {
                    storeInvestList.clear();                                        /*If keywords dont exist, remove all options*/
                }
                else {
                    List<Integer> indices3 = new ArrayList<Integer>();
                    List<Integer> storeNum = new ArrayList<Integer>(indices);
                    List<Integer> storeNum2 = new ArrayList<Integer>(indices2);
                    storeNum.retainAll(storeNum2);

                    for (int i = 0; i < keywords.length-2; i++ ) {                          /*If more than 2 keywords, retain all into one list*/
                        if (sKeywords.containsKey(keywords[i+2].toLowerCase())) {
                            indices3 = sKeywords.get(keywords[i+2].toLowerCase());
                            List<Integer> storeNum3 = new ArrayList<Integer>(indices3);
                            storeNum.retainAll(storeNum3);
                        }
                        else {
                            storeInvestList.clear();
                        }
                    }
                    if (storeInvestList.size() > 0) {
                        ArrayList<Investment> tempList = new ArrayList<Investment>(10);             /*Remaining intersection list of all keywords*/
                        for (int i = 0; i < storeNum.size(); i++) {                                 /*are the matching results*/
                            tempList.add(storeInvestList.get(storeNum.get(i)));
                        }
                        storeInvestList.clear();
                        if (tempList.size() > 0) {
                            storeInvestList.addAll(tempList);
                        }
                    }
                }

            }
            /*If only one keyword, spaceCheck is false*/
            else if (spaceCheck == false) {
                List<Integer> indices = null;

                if (sKeywords.containsKey(keyword.toLowerCase())) {
                    indices = sKeywords.get(keyword.toLowerCase());
                }
                else {
                    indices = null;
                }
                if (indices == null) {
                    storeInvestList.clear();
                }
                else {
                    ArrayList<Investment> tempList = new ArrayList<Investment>(10);
                    for (int i = 0; i < indices.size(); i++) {
                        tempList.add(storeInvestList.get(indices.get(i)));
                    }
                    storeInvestList.clear();
                    storeInvestList.addAll(tempList);
                }
            }
        }

        /*If symbol input exists, search through each storage list and remove
        investments that do not match*/
        if (symbol.length() != 0) {
            for (int i = storeInvestList.size()-1; i >= 0; i--) {
                if (!(storeInvestList.get(i).getSymbol().equalsIgnoreCase(symbol))) {
                    storeInvestList.remove(i);
                }
            }
        }
        /*If price input exists*/
        double priceHStore;
        double priceLStore;
        if (!(priceH.equals("")) || !(priceL.equals(""))) {
            if (!(priceH.equals("")) && !(priceL.equals(""))) {
                try {
                    priceHStore = Double.parseDouble(priceH);
                    priceLStore = Double.parseDouble(priceL);
                }
                catch (NumberFormatException e) {
                    return "Error: Cannot parse price range...\n";
                }
                if (priceHStore < priceLStore) {
                    return "Error: Upper bound smaller than lower!\n";
                }
                if (priceHStore == priceLStore) {
                    for (int i = storeInvestList.size()-1; i >= 0; i--) {
                        if (storeInvestList.get(i).getPrice() != priceHStore) {
                            storeInvestList.remove(i);
                        }
                    }
                }
                else if (priceHStore > priceLStore) {
                    for (int i = storeInvestList.size()-1; i >= 0; i--) {
                        if (storeInvestList.get(i).getPrice() > priceHStore || storeInvestList.get(i).getPrice() < priceLStore) {
                            storeInvestList.remove(i);
                        }
                    }
                }
            }
            else if (priceH.equals("") && !(priceL.equals(""))) {
                try {
                    priceLStore = Double.parseDouble(priceL);
                }
                catch (NumberFormatException e) {
                    return "Error: Cannot parse price range...\n";
                }
                for (int i = storeInvestList.size()-1; i >= 0; i--) {
                    if (storeInvestList.get(i).getPrice() < priceLStore) {
                        storeInvestList.remove(i);
                    }
                }
            }
            else if (!(priceH.equals("")) && priceL.equals("")) {
                try {
                    priceHStore = Double.parseDouble(priceH);
                }
                catch (NumberFormatException e) {
                    return "Error: Cannot parse price range...\n";
                }
                for (int i = storeInvestList.size()-1; i >= 0; i--) {
                    if (storeInvestList.get(i).getPrice() > priceHStore) {
                        storeInvestList.remove(i);
                    }
                }
            }
        }

        /*Print remaining investments in each list that satisfy all criteria*/
        String output = "";
        output = "\n----------------------Search Results----------------------";
        output += "\nType -Symbol, Name, Quantity, Price, Book Value\n\n";

        for (int i = 0; i < storeInvestList.size(); i++) {
            if (storeInvestList.get(i) instanceof Stock) {
               output += "Stock "+storeInvestList.get(i);                   /*Check if result is stock or mutual fund*/
            }
            else if (storeInvestList.get(i) instanceof MutualFund) {
                output += "Mutual Fund "+storeInvestList.get(i);
            }
            output += "\n";
        }

        /*If both lists are empty, no investment satisfied criteria*/
        if (storeInvestList.size() == 0) {
            output += "0 Results Found!";
            if (investList.size() == 0) {
                output +="\n\nNo Investments.";
            }
        }
        return output;
    }


    /**
     * Checks if value exists in stock list
     * @param temp object of type Stock to iterate through and check for exactness
     * @return boolean value indicating true for exactness found and false for not found
     */
    public int checkStockUnique(Stock temp) {
        for(int i=0; i<this.investList.size(); i++){
            Investment invest1 = this.investList.get(i);
            if (invest1.equals(temp)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if value exists in mutual fund list
     * @param temp object of type MutualFund to iterate through and check for exactness
     * @return boolean value indicating true for exactness found and false for not found
     */
    public int checkFundUnique(MutualFund temp) {
        for(int i=0; i<this.investList.size(); i++){
            Investment invest1 = this.investList.get(i);
            if (invest1.equals(temp)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Writes the content of the current investment list to a specified file
     * @param fileName the specified command line filename from user
     * @return string with message output
     */
    public String saveFile(String fileName) {
        PrintWriter outputStream = null;
        try {
            if (!(fileName.equalsIgnoreCase("none123456abc"))) {
                outputStream = new PrintWriter(new FileOutputStream(fileName));
            }
            else {
                return "\nFile could not be saved. Exiting...\n";
            }
        }
        catch (FileNotFoundException e) {
            return "\nFile could not be saved. Exiting...\n";
        }
        for (int i = 0; i < investList.size(); i++) {
            if (this.investList.get(i) instanceof Stock) {
                outputStream.println("Stock"+this.investList.get(i));
            }
            else if (this.investList.get(i) instanceof MutualFund) {
                outputStream.println("MutualFund"+this.investList.get(i));
            }
        }
        outputStream.close();

        return "\nSaved! Goodbye!\n";
    }

    /**
     * Reads and writes the content of the specified file to the investment list
     * @param fileName the specified command line filename from user
     * @return string with message output
     */
    public String parseFile(String fileName) {
        Scanner inputStream = null;

        try {
            if (!(fileName.equalsIgnoreCase("none123456abc"))) {
                inputStream = new Scanner(new FileInputStream(fileName));
            }
            else {
                return "\nNo File Given. Data Will Not Be Saved...\n";
            }
        }
        catch (FileNotFoundException e) {
            return "\nNo Data In File...\n";
        }

        String[] parts;
        String[] data;
        investList.clear();

        while (inputStream.hasNext()) {
            String line = inputStream.nextLine();
            parts = line.split("-");                                            /*Iterate each line in file*/
            data = parts[1].split(",");                                         /*Split by '-' to get type,*/
                                                                                /*Split by ',' to get each data input*/
            String symbol = data[0];
            String name = data[1].substring(1);
            String quantityS = data[2].substring(1);                            /*Substring as first input is space*/
            String priceS = data[3].substring(1);
            String bookValueS = data[4].substring(1);
            int quantity = -1;
            double price = -1;
            double bookValue = -1;

            try {
                quantity = Integer.parseInt(quantityS);
                price = Double.parseDouble(priceS);                             /*Parse all number values from strings*/
                bookValue = Double.parseDouble(bookValueS);
            }
            catch (NumberFormatException e){
            }

            if (parts[0].equalsIgnoreCase("Stock")) {
                Stock temp = null;
                try {
                    temp = new Stock(symbol, name, quantity, price, bookValue);
                }
                catch (EmptyFieldException | QuantityFieldException | PriceFieldException | BookvalueFieldException e) {
                    return e.getMessage();
                }
                if (temp.getName().contains(" ")) {
                    for (String retval: temp.getName().toLowerCase().split(" ")) {
                        List<Integer> indices = new ArrayList<Integer>();
                        List<Integer> indices2;
                        if (sKeywords.containsKey(retval.toLowerCase())) {
                            indices2 = sKeywords.get(retval.toLowerCase());
                            indices2.add(investList.size());                        /*Multiple keywords, split*/
                            sKeywords.put(retval.toLowerCase(), indices2);          /*Check if keyword exists, if so then update, else add new list*/
                        }
                        else {
                            indices.add(investList.size());
                            sKeywords.put(retval.toLowerCase(), indices);
                        }
                    }
                }
                else {
                    List<Integer> indices = new ArrayList<Integer>();
                    List<Integer> indices2;
                    if (sKeywords.containsKey(temp.getName().toLowerCase())) {      /*One keyword*/
                        indices2 = sKeywords.get(temp.getName().toLowerCase());     /*Check if keyword exists, if so then update, else add new list*/
                        indices2.add(investList.size());
                        sKeywords.put(temp.getName().toLowerCase(), indices2);
                    }
                    else {
                        indices.add(investList.size());
                        sKeywords.put(temp.getName().toLowerCase(), indices);
                    }
                }
                investList.add(temp);
            }
            else if (parts[0].equalsIgnoreCase("MutualFund")) {
                MutualFund temp = null;
                try {
                    temp = new MutualFund(symbol, name, quantity, price, bookValue);
                }
                catch (EmptyFieldException | QuantityFieldException | PriceFieldException | BookvalueFieldException e) {
                    return e.getMessage();
                }
                if (temp.getName().contains(" ")) {
                    for (String retval: temp.getName().toLowerCase().split(" ")) {
                        List<Integer> indices = new ArrayList<Integer>();
                        List<Integer> indices2;                                     /*Multuple keywords, split*/
                        if (sKeywords.containsKey(retval.toLowerCase())) {          /*Check if keyword exists, if so then update, else add new list*/
                            indices2 = sKeywords.get(retval.toLowerCase());
                            indices2.add(investList.size());
                            sKeywords.put(retval.toLowerCase(), indices2);
                        }
                        else {
                            indices.add(investList.size());
                            sKeywords.put(retval.toLowerCase(), indices);
                        }
                    }
                }
                else {
                    List<Integer> indices = new ArrayList<Integer>();
                    List<Integer> indices2;
                    if (sKeywords.containsKey(temp.getName().toLowerCase())) {
                        indices2 = sKeywords.get(temp.getName().toLowerCase());     /*One keyword*/
                        indices2.add(investList.size());                            /*Check if keyword exists, if so then update, else add new list*/
                        sKeywords.put(temp.getName().toLowerCase(), indices2);
                    }
                    else {
                        indices.add(investList.size());
                        sKeywords.put(temp.getName().toLowerCase(), indices);
                    }
                }
                investList.add(temp);
            }

        }
        return "\nLoaded!\n";
    }

    /**
     * Method to remove all panels from frame, used during transitions
     */
    public void removePanels() {
        remove(introPanel);
        remove(buyFinal);
        remove(sellFinal);
        remove(updateFinal);
        remove(gainFinal);
        remove(searchFinal);
        remove(scrollPanel);
        remove(individualGainsPanel);
        remove(searchResultsPanel);
    }

    /**
     * Method to determine correct input during any function
     * @param input string that is inputted into a text field
     * @param format signifies what type of input is being checked
     * @return true if input is correct, false otherwise
     */
    public boolean validateFormat(String input, int format){
        try {
            if (input.equals("")) {
                throw new EmptyFieldException();
            }
            switch(format){
                case V_SYMBOL:
                    if (!(input.matches("^[a-zA-Z0-9]*$"))) return false;
                    break;
                case V_NAME:
                    if (input.contains(",") || input.contains("-")) return false;
                    break;
                case V_QUANTITY:
                    int quantity = -1;
                    if (input != null) {
                        quantity = Integer.parseInt(input);
                    }
                    if (quantity < 1) {
                        throw new QuantityFieldException();
                    }
                    break;
                case V_PRICE:
                    double price = -1.0;
                    if (input != null) {
                        price = Double.parseDouble(input);
                    }
                    if (price <= 0.0) {
                        throw new PriceFieldException();
                    }
                    break;
                default:
                    return false;
            }
        }
        catch (NumberFormatException e) {
            return false;
        }
        catch (EmptyFieldException e) {
            return false;
        }
        catch (PriceFieldException e) {
            return false;
        }
        catch (QuantityFieldException e) {
            return false;
        }
        return true;
    }

    /**
     * Method to determine correct input during search function
     * @param input string that is inputted into a text field
     * @param format signifies what type of input is being checked
     * @return true if input is correct, false otherwise
     */
    public boolean validateFormatSearch(String input, int format){
        try {
            switch(format){
                case S_SYMBOL:
                    if (!(input.equals(""))) {
                         if (!(input.matches("^[a-zA-Z0-9]*$"))) return false;
                    }
                    break;
                case S_PRICE:
                    if (!(input.equals(""))) {
                        double price = -1.0;
                        if (input != null) {
                            price = Double.parseDouble(input);
                        }
                        if (price <= 0.0) {
                            throw new PriceFieldException();
                        }
                    }
                    break;
                case S_KEYWORDS:
                    if (!(input.equals(""))) {
                        if (input.replace(" ", "").length() == 0) throw new EmptyFieldException();
                    }
                    break;
                default:
                    return false;
            }
        }
        catch (EmptyFieldException e) {
            return false;
        }
        catch (NumberFormatException e) {
            return false;
        }
        catch (PriceFieldException e) {
            return false;
        }
        return true;
    }

    /**
     * Method to save portfolio upon clicking 'x' button on window
     * @param fileName file name to save portfolio to
     */
    public void closeProgram(String fileName) {
        saveFile(fileName);
        System.exit(0);
    }
}
