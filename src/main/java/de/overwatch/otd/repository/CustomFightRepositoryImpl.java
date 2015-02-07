package de.overwatch.otd.repository;


import de.overwatch.otd.service.UserStatistic;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomFightRepositoryImpl implements CustomFightRepository{

    @PersistenceContext
    private EntityManager em;

    public UserStatistic getUserStatistic(Integer userId){

        String sql = "" +
                "SELECT " +
                "       SUM(CASE WHEN attacker_id = :userId THEN 1 ELSE 0 END) as attacksTotal," +
                "       SUM(CASE WHEN attacker_id = :userId && outcome = 'ATTACKER_WON' THEN 1 ELSE 0 END) as attacksWon," +
                "       SUM(CASE WHEN defender_id = :userId THEN 1 ELSE 0 END) as defendsTotal," +
                "       SUM(CASE WHEN defender_id = :userId && outcome = 'DEFENDER_WON' THEN 1 ELSE 0 END) as defendsWon" +
                "   FROM fight" +
                "   WHERE attacker_id = :userId OR defender_id = :userId" +
                "   ORDER BY attacker_id, defender_id";

        Query query = em.createNativeQuery(sql);
        query.setParameter("userId", userId);
        List<Object[]> rows = query.getResultList();

        if(rows==null || rows.size() == 0){ return null;}

        UserStatistic userStatistic = new UserStatistic();
        for(Object[] row: rows){
            if(row[0]!=null){
                userStatistic.setAttacksTotal(userStatistic.getAttacksTotal() + ((BigDecimal)row[0]).intValue());
            }
            if(row[1]!=null){
                userStatistic.setAttacksWon(userStatistic.getAttacksWon() + ((BigDecimal) row[1]).intValue());
            }
            if(row[2]!=null){
                userStatistic.setDefendsTotal(userStatistic.getDefendsTotal() + ((BigDecimal) row[2]).intValue());
            }
            if(row[3]!=null){
                userStatistic.setDefendsWon(userStatistic.getDefendsWon() + ((BigDecimal) row[3]).intValue());
            }




        }


        return userStatistic;

    }


}
