import useCashReport from './useCashReport';
import PageHeader from '@salesforce/design-system-react/components/page-header';
import Icon from '@salesforce/design-system-react/components/icon';
import Card from '@salesforce/design-system-react/components/card';
import FormattedNumberField from 'components/base/formattedNumberField';

const CashOverview = () => {
  const [cashReport, spinner] = useCashReport();

  return (
    <>
      {spinner}

      <PageHeader
        icon={<Icon category="standard" name="opportunity" />}
        label="Übersicht"
        title="Einnahmen, Ausgaben und Kassenstand"
        truncate
        variant="object-home"
      />

      {!!cashReport && (
        <div className="slds-grid slds-grid_pull-padded-small slds-wrap">
          <div className="slds-col slds-col_padded slds-size_full slds-medium-size_1-of-3 slds-var-m-vertical_small">
            <Card
              heading="Einnahmen"
              icon={<Icon category="standard" name="investment_account" />}
              bodyClassName="slds-text-align_center">
              <FormattedNumberField value={cashReport.totalIncome} className="slds-text-heading_large" />
            </Card>
          </div>

          <div className="slds-col slds-col_padded slds-size_full slds-medium-size_1-of-3 slds-var-m-vertical_small">
            <Card
              heading="Ausgaben"
              icon={<Icon category="standard" name="expense" />}
              bodyClassName="slds-text-align_center">
              <FormattedNumberField value={cashReport.totalExpenses} className="slds-text-heading_large" />
            </Card>
          </div>

          <div className="slds-col slds-col_padded slds-size_full slds-medium-size_1-of-3 slds-var-m-vertical_small">
            <Card
              heading="Kassenstand"
              icon={<Icon category="standard" name="entity" />}
              bodyClassName="slds-text-align_center">
              <FormattedNumberField value={cashReport.currentCash} className="slds-text-heading_large " />
            </Card>
          </div>
        </div>
      )}
    </>
  );
};

export default CashOverview;
