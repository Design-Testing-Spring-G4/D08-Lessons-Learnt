
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rendezvous;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	//The average and the standard deviation of users per rendezvous.
	@Query("select avg(r.attendants.size), stddev(r.attendants.size) from Rendezvous r")
	Double[] avgStddevUserPerRendezvous();

	//The ratio of users who have ever created a rendezvous versus the users who have never created any rendezvouses.
	@Query("select (select count(u) from User u where u.rendezvous.size>0)*1.0/count(u) from User u where u.rendezvous.size=0")
	Double[] ratioRendezvousVsNotRendezvous();

	//The top-10 rendezvouses in terms of users who have RSVPd them.
	@Query("select r from Rendezvous r order by r.attendants.size")
	Collection<Rendezvous> topTenRendezvous();

	//The average and the standard deviation of announcements per rendezvous.
	@Query("select avg(r.announcements.size), stddev(r.announcements.size) from Rendezvous r")
	Double[] avgStddevAnnouncementsPerRendezvous();

	//The listing of trips that have got at least 10% more applications than the average, ordered by number of applications.
	//The rendezvouses that whose number of announcements is above 75% the average number of announcements per rendezvous.
	@Query("select r from Rendezvous r where r.announcements.size > (select avg(r.announcements.size)*1.75 from Rendezvous r)")
	Collection<Rendezvous> announcementsWithAboveAverageRendezvous();
}
