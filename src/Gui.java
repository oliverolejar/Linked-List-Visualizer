import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

    private JComboBox<Integer> userOpt;
    private JPanel listPanel;
    private JPanel frontPanel;
    private SinglyLinkedList list;
    private int nodeCount;

    public Gui(){
        list = new SinglyLinkedList();
        nodeCount = 0;

        JFrame frame = new JFrame("Linked List Visualizer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setLayout(null);

        Integer[] numOpt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        userOpt = new JComboBox<>(numOpt);
        userOpt.setFont(new Font("Arial", Font.BOLD, 25));
        ((JLabel)userOpt.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        userOpt.setBounds(470, 300, 60 ,40);
        frame.add(userOpt);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBounds(295, 300, 125,40);
        frame.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction();
            }
        });

        JButton remButton = new JButton("Remove");
        remButton.setFont(new Font("Arial", Font.BOLD, 20));
        remButton.setBounds(580, 300, 125,40);
        frame.add(remButton);
        remButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remAction();
            }
        });

        listPanel = new JPanel();
        listPanel.setSize(1000, 100);
        listPanel.setBounds(0, 150, 1000,100);
        frame.getContentPane().add(listPanel);

        frontPanel = new JPanel();
        frontPanel.setSize(1000, 50);
        frontPanel.setBounds(0,50,1000,50);
        frame.getContentPane().add(frontPanel);

        displayFront();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addAction(){
        if ( userOpt.getSelectedItem() != null && nodeCount < 10 ){
            list.addNode((int)userOpt.getSelectedItem());

            displayFront();

            if ( listPanel.getComponentCount() > 0 ) {
                listPanel.remove(listPanel.getComponent(listPanel.getComponentCount() - 1));
            }

            JLabel nodeLabel = new JLabel(Integer.toString((int)userOpt.getSelectedItem()));
            nodeLabel.setFont(new Font("Arial", Font.BOLD, 25));
            nodeLabel.setForeground(Color.BLACK);
            nodeLabel.setBounds(50+(nodeCount * 100), 100, 30, 30);
            nodeLabel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK, 5), new EmptyBorder(5,10,5,10)));

            listPanel.add(nodeLabel);

            JLabel arrowLabel = new JLabel("~>");
            arrowLabel.setFont(new Font("Arial", Font.BOLD, 30));
            arrowLabel.setForeground(Color.BLACK);
            arrowLabel.setBounds(100+(nodeCount * 100), 100, 30, 30);

            listPanel.add(arrowLabel);

            JLabel nullLabel = new JLabel("null");
            nullLabel.setFont(new Font("Arial", Font.BOLD, 25));
            nullLabel.setForeground(Color.BLACK);
            nullLabel.setBounds(150+(nodeCount * 100), 100, 30, 30);

            listPanel.add(nullLabel);

            listPanel.revalidate();
            listPanel.repaint();

            nodeCount++;

            System.out.println("Added " + (int)userOpt.getSelectedItem() );
        } else {
            System.out.println("Cannot display more than 10 nodes or no number is selected");
        }
    }

    private void remAction(){

        if ( userOpt.getSelectedItem() != null && list.contains((int)userOpt.getSelectedItem()) ){
            list.removeNode((int)userOpt.getSelectedItem());

            displayFront();

            for ( int i = 0 ; i < listPanel.getComponentCount() ; i++ ){
                Component component = listPanel.getComponent(i);
                if ( (((JLabel)component).getText()).equals(Integer.toString((int)userOpt.getSelectedItem())) ){
                    Component arrowComponent = listPanel.getComponent(i+1);
                    listPanel.remove(component);
                    listPanel.remove(arrowComponent);

                    if ( listPanel.getComponentCount() == 1 ){
                        listPanel.remove(listPanel.getComponent(0));
                    }

                    listPanel.revalidate();
                    listPanel.repaint();

                    nodeCount--;

                    System.out.println("Removed " + (int)userOpt.getSelectedItem() );

                    return;
                }
            }
        } else {
            System.out.println("Number does not exist in list or no number is selected");
        }
    }

    private void displayFront(){
        if ( list.getFront() != null ){
            frontPanel.removeAll();

            JLabel frontLabel = new JLabel("Front: " + list.getFront().getItem() );
            frontLabel.setFont(new Font("Arial", Font.BOLD, 35));
            frontLabel.setForeground(Color.BLACK);
            frontLabel.setHorizontalAlignment(SwingConstants.CENTER);

            frontPanel.add(frontLabel);

            frontPanel.revalidate();
            frontPanel.repaint();
        } else {
            frontPanel.removeAll();

            JLabel frontLabel = new JLabel("Front: null");
            frontLabel.setFont(new Font("Arial", Font.BOLD, 35));
            frontLabel.setForeground(Color.BLACK);
            frontLabel.setHorizontalAlignment(SwingConstants.CENTER);

            frontPanel.add(frontLabel);

            frontPanel.revalidate();
            frontPanel.repaint();
        }
    }

}
