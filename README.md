# Bank Account Pair Coding

This might be a decent place to put a "ledger lab" (double-entry-ledger fundamentals)
AND enable it to be done in Python as well.

(early UML to code lab)

For this assignment, we're going to pair up and build some bank accounts.

Start with building out your UML for all of this and then go into writing your tests and code. 
The UML here should help you simplify your ideas.

You should have:
* An abstract account that has all of the values that non-abstract accounts share.  These should have:
    * an accountHolder
    * a balance
    * an accountNumber
    * A record of every transaction
    * some basic methods that all accounts should share like credit, debit, getters and setter (where appropriate)

    * A checking account that has the following
        * An overdraft protection boolean that makes it so the user cannot withdraw more money than they have in their account, if true overdraft not allowed, if false overdraft allowed
    * A savings account that:
        * Gains interest
        * Has overdraft protection
    * An investment account that:
        * Gains interest
    
Also, there should be two different AccountHolder types:
* Person which has a firstName, a lastName, an email, phone number
* Business which just has a businessName
