package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Voyage;

public class VoyageDaoImpl implements IVoyageDao {

    @Override
    public Voyage save(Voyage v) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO VOYAGES(NOM_VOYAGE, PRIX) VALUES(?, ?)");
            ps.setString(1, v.getNomVoyage());
            ps.setDouble(2, v.getPrix());
            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_VOYAGE) as MAX_ID FROM VOYAGES");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                v.setIdVoyage(rs.getLong("MAX_ID"));
            }
            ps.close();
            ps2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public List<Voyage> voyagesParMC(String mc) {
        List<Voyage> voyages = new ArrayList<>();
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VOYAGES WHERE NOM_VOYAGE LIKE ?");
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Voyage v = new Voyage();
                v.setIdVoyage(rs.getLong("ID_VOYAGE"));
                v.setNomVoyage(rs.getString("NOM_VOYAGE"));
                v.setPrix(rs.getDouble("PRIX"));
                voyages.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyages;
    }

    @Override
    public Voyage getVoyage(Long id) {
        Connection conn = SingletonConnection.getConnection();
        Voyage v = new Voyage();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VOYAGES WHERE ID_VOYAGE = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setIdVoyage(rs.getLong("ID_VOYAGE"));
                v.setNomVoyage(rs.getString("NOM_VOYAGE"));
                v.setPrix(rs.getDouble("PRIX"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public Voyage updateVoyage(Voyage v) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE VOYAGES SET NOM_VOYAGE=?, PRIX=? WHERE ID_VOYAGE=?");
            ps.setString(1, v.getNomVoyage());
            ps.setDouble(2, v.getPrix());
            ps.setLong(3, v.getIdVoyage());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void deleteVoyage(Long id) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM VOYAGES WHERE ID_VOYAGE = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
