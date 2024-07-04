package Formatos;

import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

public class CentrarForma {
        
    public static void CPanel(JDesktopPane pan, JInternalFrame inf, int i) {
        Dimension desktopSize = pan.getSize();
        Dimension jifSize = inf.getSize();

        switch (i) {
            case 1: // Caso 1: Centrar a la izquierda
                inf.setLocation((desktopSize.width - jifSize.width) / 2 - 220,
                                 (desktopSize.height - jifSize.height) / 2 - 20);
                break;
            case 2: // Caso 2: Centrar en el medio
                inf.setLocation((desktopSize.width - jifSize.width) / 2,
                                 (desktopSize.height - jifSize.height) / 2);
                break;
            case 3: // Caso 3: Centrar a la derecha
                inf.setLocation((desktopSize.width - jifSize.width) / 2 + 450,
                                 (desktopSize.height - jifSize.height) / 2);
                break;
            default:
                // Opcional: Puedes manejar otros casos o lanzar una excepción si el valor de 'i' no es válido.
                break;
        }
    }
}

