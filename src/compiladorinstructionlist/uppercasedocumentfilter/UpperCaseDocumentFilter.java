package compiladorinstructionlist.uppercasedocumentfilter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

// Classe que transforma letras para maiúsculo
public class UpperCaseDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        text = text.toUpperCase();
        super.insertString(fb, offset, text, attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        text = text.toUpperCase();
        super.replace(fb, offset, length, text, attrs);
    }
}
