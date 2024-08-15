import { makeStyles } from '@material-ui/core/styles';
import EveningOverview from '../evenings/eveningOverview';
import ExpensesOverview from '../expenses/expensesOverview';
import EarningsOverview from '../earnings/earningsOverview';
import ReportView from '../reports/reportView';
import CashOverview from '../overview/cashOverview';
import classNames from 'classnames';

const contentMap = new Map([
  ['overview', CashOverview],
  ['evenings', EveningOverview],
  ['earnings', EarningsOverview],
  ['expenses', ExpensesOverview],
  ['reports', ReportView]
]);

const useStyles = makeStyles({
  '@media screen and (min-width: 500px)': {
    container: {
      maxHeight: '90vh'
    }
  }
});

const NavigationContent = ({ activeContent }) => {
  const classes = useStyles();
  const Content = contentMap.get(activeContent);

  return (
    <div className={classNames('slds-var-p-around_small', classes.container)}>
      {<Content /> ?? <h1 className="slds-text-color_error">Error</h1>}
    </div>
  );
};

export default NavigationContent;
