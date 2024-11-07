package GerenciarGUI;

import javax.swing.*;
		import java.awt.*;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;
		import java.util.ArrayList;
		
public class gerenciarFrutasGUI {
		    private ArrayList<String> frutas;
		    private DefaultListModel<String> listModel;
		    private JList<String> list;

		    public gerenciarFrutasGUI() {
		        frutas = new ArrayList<>();
		        listModel = new DefaultListModel<>();

		        JFrame frame = new JFrame("Gerenciador de Frutas");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(600, 300);
		        frame.setLayout(new BorderLayout());

		        JPanel panel = new JPanel();
		        panel.setLayout(new FlowLayout());

		        JTextField frutaField = new JTextField(15);
		        panel.add(frutaField);

		        JButton addButton = new JButton("Adicionar");
		        panel.add(addButton);

		        JButton modifyButton = new JButton("Modificar");
		        modifyButton.setEnabled(false);
		        panel.add(modifyButton);

		        JButton removeButton = new JButton("Remover");
		        removeButton.setEnabled(false);
		        panel.add(removeButton);

		        frame.add(panel, BorderLayout.NORTH);

		        list = new JList<>(listModel);
		        JScrollPane scrollPane = new JScrollPane(list);
		        frame.add(scrollPane, BorderLayout.CENTER);

		        JButton listButton = new JButton("Listar Frutas");
		        frame.add(listButton, BorderLayout.SOUTH);

		        addButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                String novaFruta = frutaField.getText();
		                if (!novaFruta.isEmpty()) {
		                    frutas.add(novaFruta);
		                    listModel.addElement(novaFruta);
		                    frutaField.setText("");
		                    JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada.");
		                }
		            }
		        });

		        modifyButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                int selectedIndex = list.getSelectedIndex();
		                if (selectedIndex != -1) {
		                    String frutaSelecionada = listModel.getElementAt(selectedIndex);
		                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
		                    if (novaFruta != null && !novaFruta.isEmpty()) {
		                        frutas.set(selectedIndex, novaFruta);
		                        listModel.set(selectedIndex, novaFruta);
		                        JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta);
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar.");
		                }
		            }
		        });

		        removeButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                int selectedIndex = list.getSelectedIndex();
		                if (selectedIndex != -1) {
		                    String frutaRemovida = listModel.getElementAt(selectedIndex);
		                    frutas.remove(selectedIndex);
		                    listModel.remove(selectedIndex);
		                    JOptionPane.showMessageDialog(frame, "Fruta " + frutaRemovida + " foi removida.");
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover.");
		                }
		            }
		        });

		        listButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                if (frutas.isEmpty()) {
		                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta cadastrada.");
		                } else {
		                    StringBuilder listaFrutas = new StringBuilder("Frutas cadastradas:\n");
		                    for (String fruta : frutas) {
		                        listaFrutas.append("- ").append(fruta).append("\n");
		                    }
		                    JOptionPane.showMessageDialog(frame, listaFrutas.toString());
		                }
		            }
		        });

		        list.addListSelectionListener(e -> {
		            boolean selectionValid = list.getSelectedIndex() != -1;
		            modifyButton.setEnabled(selectionValid);
		            removeButton.setEnabled(selectionValid);
		        });

		        frame.setVisible(true);
		    }

		    public static void main(String[] args) {
		    	SwingUtilities.invokeLater(new Runnable() {
		    		@Override
		    		public void run() {
		    			new gerenciarFrutasGUI();
		    		}
		    	});
		    }
	}
