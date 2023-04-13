package HW4;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class homeworkfour {
    public static int q = 0;
    public static String nextString = "";
    public static ArrayList<String> clumps = new ArrayList<String>();
    public static void main(String[] args) throws IOException {
        HashMap<String, String> tokens = new HashMap<>();
        tokens.put("+", "addition operator");
        tokens.put("-", "subtraction operator");
        tokens.put("*", "multiplication operator");
        tokens.put("/", "division operator");
        tokens.put("%", "modulus operator");
        tokens.put("==", "assignment operator");
        tokens.put("=", "equals operator");
        tokens.put("<", "less than operator");
        tokens.put("<=", "less than or equal operator");
        tokens.put(">", "greater than operator");
        tokens.put(">=", "greater than or equal operator");
        tokens.put("&&", "logical and operator");
        tokens.put("||", "logical or operator");
        tokens.put("VARIABLE_ID", "variable identifier");
        tokens.put("INTEGER_LITERAL", "integer literal");
        tokens.put("FLOATING_LITERAL", "floating point literal");
        tokens.put("{", "left curly");
        tokens.put("}", "right curly");
        tokens.put("(", "left parenthesis");
        tokens.put(")", "right parenthesis");
        tokens.put(";", "semi colon");
        tokens.put("if", "if statement");
        tokens.put("else", "else statement");
        tokens.put("while", "while statement");

        String filename = "C:\\Users\\zeroz\\Dropbox\\GSU\\2023\\Spring\\CSC 4330\\exams\\HW4\\hw4.txt";
        
        
        FileReader reader = new FileReader(filename);
        int character;
        
       
        String temp = "";
       while ((character = reader.read()) != -1) {
            char currentChar = (char) character;
            System.out.print(currentChar);
           
            if (currentChar == '('){
                temp += currentChar;
                clumps.add(temp);
                temp = "";
            } else if (currentChar == ')'){
                clumps.add(temp);
                clumps.add(String.valueOf(currentChar));
                temp = "";
            } else if (currentChar == ';'){
                clumps.add(temp);
                clumps.add(String.valueOf(currentChar));
                temp = "";
            } else  if (currentChar!= ' '){
                temp += currentChar;
            }
            else {
                    clumps.add(temp);
                    temp = "";
                }
            
          
       }
      
       clumps.add(temp);
       System.out.println();
       for (int i = 0; i<clumps.size(); i++){
            clumps.set(i, clumps.get(i).replaceAll("\\s+", ""));
            
       }
       
       for (int i = 0; i<clumps.size(); i++){
        if(clumps.get(i).equals("") || clumps.get(i).equals(" ")){
            clumps.remove(i);
        }
        
   }
   for (int i = 0; i<clumps.size(); i++){
    if(clumps.get(i).equals("") || clumps.get(i).equals("\t")){
        clumps.remove(i);
       
    }
   }
    
        System.out.println("");
    nextString = clumps.get(0).toString();
        stmt();
        
//    for (int i = 0; i<clumps.size();i++){
//         System.out.println(clumps.get(i));
//    }
        
    }
    public static String tokenizer(String x){
        HashMap<String, String> tokens = new HashMap<>();
        tokens.put("+", "ADD_OP");
        tokens.put("-", "SUB_OP");
        tokens.put("*", "MULT_OP");
        tokens.put("/", "DIV_OP");
        tokens.put("%", "MOD_OP");
        tokens.put("=", "ASSIGN_OP");
        tokens.put("==", "EQUALS_OP");
        tokens.put("!=", "NOTEQUALS_OP");
        tokens.put("<", "LESS_THAN_OP");
        tokens.put("<=", "LESS_EQUAL_OP");
        tokens.put(">", "GREAT_THAN_OP");
        tokens.put(">=", "GREAT_EQUAL_OP");
        tokens.put("&&", "LOG_AND_OP");
        tokens.put("||", "LOG_OR_OP");
        tokens.put("VARIABLE_ID", "VARIABLE_ID");
        tokens.put("INTEGER_LITERAL", "INTEGER_LITERAL");
        tokens.put("FLOATING_LITERAL", "FLOTAING_LITERAL");
        tokens.put("{", "LEFT_CURLY");
        tokens.put("}", "RIGHT_CURLY");
        tokens.put("(", "LEFT_PAREN");
        tokens.put(")", "RIGHT_PAREN");
        tokens.put(";", "SEMICOLON");
        tokens.put("if", "IF_STATEMENT");
        tokens.put("else", "ELSE_STATEMENT");
        tokens.put("while", "WHILE_STATEMENT");
        Pattern pattern = Pattern.compile("[0-9]+");
        Pattern pattern2 = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(x);
        Matcher matcher2 = pattern2.matcher(x);
        if (matcher.matches()){
            if (x.contains(".")){
                return tokens.get("FLOATING_LITERAL");
            } else {
                return tokens.get("INTEGER_LITERAL");
            }
        }
        else if (x.equals("if")){return tokens.get("if");}
        else if (x.equals("else")){return tokens.get("else");}
        else if (x.equals("while")){return tokens.get("while");}
        else if (matcher2.matches()){
            return tokens.get("VARIABLE_ID");
        } 
        else if (x.equals("||")){return tokens.get("||");}
        else if (x.equals("&&")){return tokens.get("&&");}
        else if (x.equals(">=")){return tokens.get(">=");}
        else if (x.equals(">")){return tokens.get(">");}
        else if (x.equals("<=")){return tokens.get("<=");}
        else if (x.equals("<")){return tokens.get("<");}
        else if (x.equals("=")){return tokens.get("=");}
        else if (x.equals("==")){return tokens.get("==");}
        else if (x.equals("%")){return tokens.get("%");}
        else if (x.equals("/")){return tokens.get("/");}
        else if (x.equals("*")){return tokens.get("*");}
        else if (x.equals("-")){return tokens.get("-");}
        else if (x.equals("+")){return tokens.get("+");}
        else if (x.equals("{")){return tokens.get("{");}
        else if (x.equals("}")){return tokens.get("}");}
        else if (x.equals("(")){return tokens.get("(");}
        else if (x.equals(")")){return tokens.get(")");}
        else if (x.equals(";")){return tokens.get(";");}
        
        else { return "UNKOWN";}
    }
public static void stmt(){
    if (tokenizer(nextString) == "IF_STATEMENT"){
        getNext(clumps, q);        
        ifstmt();
        } 
        else if (tokenizer(nextString) == "WHILE_STATEMENT"){
            whileStatement();
         }
        else if (tokenizer(nextString) == "LEFT_CURLY"){
            block();
            }
        else {expr();}
}

public static void ifstmt(){
    System.out.println(nextString + " " + tokenizer(nextString) + "if1");
    getNext(clumps, q);
    
    if (tokenizer(nextString) != "LEFT_PAREN"){
        System.out.println("if error lp");
    } else {
        System.out.println(nextString + " " + tokenizer(nextString)+ "if2");
        getNext(clumps, q);
        boolExpr();
        if (tokenizer(nextString) != "RIGHT_PAREN"){
            System.out.println("if error rp");
        } else{
            System.out.println(nextString + " " + tokenizer(nextString)+ "if3");
            getNext(clumps, q);
            if(tokenizer(nextString) == "LEFT_CURLY"){
                block();
            } else {
                expr();
            }
        }
    }
    System.out.println(nextString + " " + tokenizer(nextString)+ "if4");
    getNext(clumps, q);
    if (tokenizer(nextString) == "ELSE_STATEMENT"){
        System.out.println(nextString + " " + tokenizer(nextString)+ "if5");
        getNext(clumps, q);
        if(tokenizer(nextString) == "LEFT_CURLY"){
            System.out.println(nextString + " " + tokenizer(nextString) + "block");
            block();
        } else {
            System.out.println(nextString + " " + tokenizer(nextString));
            expr();
            System.out.println(nextString + " " + tokenizer(nextString));
            if (tokenizer(nextString) != "SEMICOLON"){
                System.out.println("if stmnt semicolon error");
            }
        }
        System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
    }
}

public static void whileStatement(){
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
    if (tokenizer(nextString) != "LEFT_PAREN"){
        System.out.println("while error lp");
    }else {
        System.out.println(nextString + " " + tokenizer(nextString)+ "while");
        getNext(clumps, q);
        boolExpr();
        if (tokenizer(nextString) != "RIGHT_PAREN"){
            System.out.println("if error rp");
        } else{
            System.out.println(nextString + " " + tokenizer(nextString));
            getNext(clumps, q);
            if(tokenizer(nextString) == "LEFT_CURLY"){
                block();
            } else {
                expr();
                if (tokenizer(nextString) != "SEMICOLON"){
                    System.out.println("while stmnt semicolon error");
                }
            }
        }
    }
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
}

public static void block(){
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
    stmnt_list();
    if(tokenizer(nextString)!= "RIGHT_CURLY"){
        System.out.println("block rc error");
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
    } else {
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
    }
}

public static void stmnt_list (){
    stmt();
    if(tokenizer(nextString)!= "SEMICOLON"){
        System.out.println("stmnt list semicolon error");
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
    } else {
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
    }
}

public static void expr(){
    term();
    if (tokenizer(nextString) == "ADD_OP" || tokenizer(nextString) == "SUB_OP"){
        System.out.println(nextString + " " + tokenizer(nextString) + "expr");
        getNext(clumps, q);
        term();
    }
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
}

public static void term(){
    factor();
    if (tokenizer(nextString) == "MUL_OP" || tokenizer(nextString) == "DIV_OP" || tokenizer(nextString) == "MOD_OP"){
        System.out.println(nextString + " " + tokenizer(nextString)+ "term");
        getNext(clumps, q);
        factor();
    }
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
}

public static void factor(){
    if(tokenizer(nextString) == "VARIABLE_ID" || tokenizer(nextString) == "INTEGER_LITERAL" || tokenizer(nextString) == "FLOATING_LITERAL"){
        System.out.println(nextString + " " + tokenizer(nextString)+ "factor");
        getNext(clumps, q);
    } else if (tokenizer(nextString) == "LEFT_PAREN"){
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
        expr();
        if (tokenizer(nextString) != "RIGHT_PAREN"){
            System.out.println("factor rp error");
            System.out.println(nextString + " " + tokenizer(nextString));
            getNext(clumps, q);
        } else {
            System.out.println(nextString + " " + tokenizer(nextString));
            getNext(clumps, q);
        }
    }
}

public static void boolExpr(){
    boolTerm();
    if (tokenizer(nextString) == "GREAT_EQUAL_OP" || tokenizer(nextString) == "GREAT_THAN_OP" || tokenizer(nextString) == "LESS_THAN_OP" || tokenizer(nextString) =="LESS_EQUAL_OP"){
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
        boolTerm();
    }
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
}

public static void boolTerm(){
    boolAnd();
    if (tokenizer(nextString) == "EQUALS_OP" || tokenizer(nextString) == "NOTEQUALS_OP"){
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
        boolAnd();
    }
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
}

public static void boolAnd(){
    boolOr();
    if(tokenizer(nextString) == "LOG_AND_OP"){
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
        boolOr();
    }
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
}

public static void boolOr(){
    expr();
    if(tokenizer(nextString) == "LOG_OR_OP"){
        System.out.println(nextString + " " + tokenizer(nextString));
        getNext(clumps, q);
        expr();
    }
    System.out.println(nextString + " " + tokenizer(nextString));
    getNext(clumps, q);
}
//tokenizer(nextString) == "VARIABLE_ID" || tokenizer(nextString) == "INTEGER_LITERAL" || tokenizer(nextString) == "FLOATING_LITERAL"
// public static void stmt(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("stmt");
//     if (tokenizer(nextString) == "IF_STATEMENT"){
//         getNext(clumps, q);
//         ifstmt();
//     } 
//     else if (tokenizer(nextString) == "WHILE_STATEMENT"){
//         getNext(clumps, q);
//         whileStatement();
//     }
//     else if (tokenizer(nextString) == "LEFT_CURLY"){
        
//         block();
//     }
//     else expr();
// }

// public static void whileStatement(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     if(tokenizer(nextString) != "LEFT_PAREN"){
//         System.out.println("while error");
//     } else {
//         getNext(clumps, q);
//         BEXPR();
//         if (tokenizer(nextString) != "RIGHT_PAREN"){
//             System.out.println("while error 2");
//         } else {
//             getNext(clumps, q);
//             if (tokenizer(nextString) != "LEFT_CURLY"){
//                 System.out.println("while error 3");
//             } else {
//                 getNext(clumps, q);
//                 block();
//                 if(tokenizer(nextString) != "RIGHT_CURLY"){
//                     System.out.println("while error 4");
//                 } else {
//                     getNext(clumps, q);
//                 }
//             }
//         }
//     }
// }
// public static void ifstmt (){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     getNext(clumps, q);
//     if (tokenizer(nextString) != "LEFT_PAREN"){
//         System.out.println("if error1");
//     } else {
//         getNext(clumps, q);
//         BEXPR();
//         if (tokenizer(nextString) != "RIGHT_PAREN"){
//             System.out.println("if error2");
//         } else {
//             getNext(clumps, q);
//             stmt();
//             if (tokenizer(nextString) == "ELSE_STATEMENT"){
//                 System.out.println(nextString + " " + tokenizer(nextString));
//                 getNext(clumps, q);
//                 stmt();
//                 if (tokenizer(nextString) != "SEMICOLON"){
//                     System.out.println("if error3");
//                 } else {
//                     getNext(clumps, q);
//                     block();
//                 }
//             }
//         }
//     }
// }
// public static void BEXPR(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("BEXPR");
//     BTERM();
//     while (tokenizer(nextString) == "GREAT_EQUAL_OP" || tokenizer(nextString) == "GREAT_THAN_OP" || tokenizer(nextString) == "LESS_THAN_OP" || tokenizer(nextString) =="LESS_EQUAL_OP"){
//         getNext(clumps, q);
//         BTERM();
//     }
// }

// public static void BTERM(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("BTERM");
//     BAND();
//     while(tokenizer(nextString) == "EQUALS_OP"|| tokenizer(nextString) == "NOTEQUALS_OP"){
//         getNext(clumps, q);
//         BAND();
//     }
// }

// public static void BAND(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("BAND");
//     BOR();
//     while (tokenizer(nextString) == "AND_OP"){
//         getNext(clumps, q);
//         BOR();
//     }
// }

// public static void BOR(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("BOR");
//     expr();
//     while (tokenizer(nextString) == "OR_OP"){
//         getNext(clumps, q);
//         expr();
//     }
// }
// public static void factor(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("FACTOR");
//     getNext(clumps, q);
//     if (tokenizer(nextString) == "VARIABLE_ID" || tokenizer(nextString) == "INTEGER_LITERAL" || tokenizer(nextString) == "FLOATING_LITERAL"){
//         getNext(clumps, q);
//     } else {
//         if (tokenizer(nextString) == "LEFT_PAREN"){
//             getNext(clumps, q);
//             expr();
//             System.out.println(nextString + " " + tokenizer(nextString));
//             if (tokenizer(nextString) != "RIGHT_PAREN"){
//                 getNext(clumps, q);
//             } else {System.out.println("factor error");}
//         }else {System.out.println("factor error 1");}
//     }
    
// }

// public static void expr(){
//     getNext(clumps, q);
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("EXPR");
    
//     term();
//     while(tokenizer(nextString) == "ADD_OP" || tokenizer(nextString) == "SUB_OP"){
//         getNext(clumps, q);
//         term();
//     }
// }

// public static void block(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("BLOCK");
//     if (tokenizer(nextString) != "LEFT_CURLY"){
//         System.out.println("block error 1");
//     } else {
//         getNext(clumps, q);
//         stmtList();
//         if(tokenizer(nextString) != "RIGHT_CURLY"){
//             System.out.println("block error 2");
//         }
//     }
// }

// public static void stmtList(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     getNext(clumps, q);
//     stmt();
//     System.out.println("StmtList");
//     while(tokenizer(nextString) != "SEMICOLON"){
//         getNext(clumps, q);
//         stmt();
//         getNext(clumps, q);
//     }// else{getNext(clumps, q);}
//     getNext(clumps, q);
// }

// public static void term(){
//     System.out.println(nextString + " " + tokenizer(nextString));
//     System.out.println("term");
//     factor();
//     while (tokenizer(nextString) == "MUL_OP" || tokenizer(nextString) == "DIV_OP" || tokenizer(nextString) == "MOD_OP"){
//         getNext(clumps, q);
//         factor();
//     }
// }
//----------------------------------


//     public static void stmt (){
//         System.out.println(nextString + " " + tokenizer(nextString));
//         if(nextString.equals("if")){
//             ifstmt();
//         }
//         if(nextString == "{"){

//         }
//         if(tokenizer(nextString) == "FLOATING_LITERAL" || tokenizer(nextString) == "INTEGER_LITERAL" || tokenizer(nextString) == "VARIABLE_ID"){

//         }
//         if(tokenizer(nextString) == "WHILE_STATEMENT"){

//         }
//     }

//     public static void ifstmt (){
//         if(!nextString.equals("if")){
//             System.out.println(" if error");
            
//         } else {

//             getNext(clumps, q);
//             if (!nextString.equals("(")){
//                 System.out.println("( error");
//             } else {
//                 System.out.println(nextString + " " + tokenizer(nextString));
//                 getNext(clumps, q);
                
//                 booleanExpression();
//             }
//         }
//     }

//  public static void booleanExpression(){
//     bTerm();
//     while (nextString.equals(">") || nextString.equals( "<") || nextString.equals( ">=") || nextString.equals( "<=")){
//         System.out.println(nextString + " " + tokenizer(nextString));
//         getNext(clumps, q);
//         bTerm();

//     }
//  }
//  public static void bTerm(){
//     bAnd();
//     System.out.println(nextString + " " + tokenizer(nextString));
//     getNext(clumps, q);
//     while (nextString.equals( "!=") || nextString.equals( "==")){
//         System.out.println(nextString + " " + tokenizer(nextString));
//         getNext(clumps, q);
//         bAnd();

//     }
//  }
// public static void bAnd(){
//     bOr();
//     while (nextString.equals("&&")){
//         System.out.println(nextString + " " + tokenizer(nextString));
//         getNext(clumps, q);
//         bOr();
//     }
       

//     }

// public static void bOr(){
//     expr();
//     while (nextString.equals( "||")){
//         System.out.println(nextString + " " + tokenizer(nextString));
//         getNext(clumps, q);
//         expr();
//     }
// }
//  public static void whilestmt(){

//  }
//  public static void expr(){
//     term();
//     while (nextString.equals( "+") || nextString.equals( "-")){
//         System.out.println(nextString + " " + tokenizer(nextString));
//         getNext(clumps, q);
//         term();
//     }
    
//  }
//  public static void block(){

//  }
//  public static void term(){
//     factor();
//     while (nextString.equals( "*") || nextString.equals( "/") || nextString.equals("%")){
//         System.out.println(nextString + " " + tokenizer(nextString));
//         getNext(clumps, q);
//         factor();
//     }

//  }
//  public static void factor(){
//     if(tokenizer(nextString).equals("FLOATING_LITERAL") || tokenizer(nextString).equals("INTEGER_LITERAL") || tokenizer(nextString).equals( "VARIABLE_ID") || nextString .equals("(")){
//         System.out.println (nextString + " " + tokenizer(nextString));
//         if (nextString.equals( "(")){
//             System.out.println(nextString + " " + tokenizer(nextString));
//             getNext(clumps, q);
//             expr();
//             if (!nextString.equals( ")")){
//                 System.out.println("error");
//             }
//         }
//     } else {
//         System.out.println("error");
//     }
//  }

    public static void getNext (ArrayList clumps, int x) {
        if (x < clumps.size()){
            q++;
            nextString = clumps.get(x).toString();
        }
        
    }
    
    public static void printer (String x, String y){
        System.out.println("Lexeme: " + x + ", " + "Token: " + y);
    }
}
