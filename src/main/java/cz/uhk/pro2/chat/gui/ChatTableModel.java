package cz.uhk.pro2.chat.gui;

import cz.uhk.pro2.chat.model.Message;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ChatTableModel extends AbstractTableModel {
    private List<Message> messages;

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return messages.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return messages.get(rowIndex).getFrom();
            case 1:
                return messages.get(rowIndex).getTo();
            case 2:
                return messages.get(rowIndex).getMsg();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "From";
            case 1:
                return "To";
            case 2:
                return "Message";
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }
}
