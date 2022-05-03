//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            if(openBracket==-1)break;
            int closeBracket = markdown.indexOf("]", openBracket);
            if(closeBracket==-1)break;
            int openParen = markdown.indexOf("(", closeBracket);
            if(openParen==-1)break;
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen==-1)break;
            int bracketCheck=0;
            int parenCheck=0;
            while(currentIndex < markdown.length()-1){
                int openBracketCheck=markdown.indexOf("[", closeBracket+1);
                int closeBracketCheck=markdown.indexOf("]", closeBracket+1);
                if(openBracketCheck>closeBracketCheck){
                    bracketCheck=1;
                    closeBracket=closeBracketCheck;
                    }
                else if(openBracketCheck==-1&&closeBracket!=-1){
                    closeBracket=closeBracketCheck;
                }
                else bracketCheck=0;
                int openParenCheck= markdown.indexOf("(", closeParen+1);
                int closeParenCheck= markdown.indexOf(")", closeParen+1);
                if(openParenCheck>closeParenCheck){
                    parenCheck=1;
                    closeParen=closeParenCheck;
                }
                else if(openParenCheck==-1&&closeParenCheck!=-1)
                {
                    closeParen=closeParenCheck;
                }
                else parenCheck=0;
                if(parenCheck==0&&bracketCheck==0)break;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        if(args.length<=0)return;
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
