package by.epam.fitness.util;

import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.service.OrderInformationService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.OrderInformationServiceImpl;

import java.util.Date;
import java.util.Optional;

/**
 * The type Membership valid checker.
 */
public class MembershipValidChecker {
    private OrderInformationService orderInformationService = new OrderInformationServiceImpl();

    /**
     * Is current membership valid boolean.
     *
     * @param clientId the client id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    public boolean isCurrentMembershipValid(Long clientId) throws ServiceException {
        Optional<OrderInformation> orderInformationOptional = orderInformationService.findByClientId(clientId);
        return orderInformationOptional.filter(orderInformation ->
                isCurrentDateLessEndDate(orderInformation.getMembershipEndDate())).isPresent();
    }

    private boolean isCurrentDateLessEndDate(Date endTrainDate){
        return new Date().before(endTrainDate);
    }
}
