package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.MembershipValidChecker;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.MAX_NUMBER_SYMBOLS_ATTRIBUTE;
import static by.epam.fitness.util.JspConst.MAX_NUMBER_SYMBOLS_VALUE;

public class FindCoachesCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(FindCoachesCommand.class);
    private MembershipValidChecker membershipValidChecker = new MembershipValidChecker();
    private CoachService coachService = new CoachServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
        try {
            List<Coach> coaches = coachService.findAll();
            request.setAttribute(JspConst.COACHES, coaches);
            checkAndSetIfClientHasCoach(request,clientId);
            if (!membershipValidChecker.isCurrentMembershipValid(clientId)) {
                log.info("Membership isn't valid");
            } else {
                request.setAttribute(MAX_NUMBER_SYMBOLS_ATTRIBUTE, MAX_NUMBER_SYMBOLS_VALUE);
                request.setAttribute(JspConst.MEMBERSHIP_VALID, true);
            }
            page = Page.ALL_COACHES;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ALL_COACHES;
        }
        return page;
    }

    private void checkAndSetIfClientHasCoach(HttpServletRequest request, Long clientId) throws ServiceException {
        Optional<Coach> coachOptional = coachService.findByClientId(clientId);
        coachOptional.ifPresent(coach -> request.setAttribute(JspConst.ID_OF_CLIENT_COACH, coach.getId()));
    }
}