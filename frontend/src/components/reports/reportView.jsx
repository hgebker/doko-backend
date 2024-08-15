import { useContext, useState } from 'react';
import SemesterSelection from '../base/semesterSelection';
import ReportDetails from './reportDetails';
import useReport from './useReport';
import NameSwitcherDropdown from 'components/base/nameSwitcherDropdown';
import { MobileContext } from 'app';

import PageHeader from '@salesforce/design-system-react/components/page-header';
import Icon from '@salesforce/design-system-react/components/icon';
import Button from '@salesforce/design-system-react/components/button';
import PageHeaderControl from '@salesforce/design-system-react/components/page-header/control';

function HeaderControls(onRefresh) {
  return (
    <PageHeaderControl>
      <Button
        assistiveText={{ icon: 'Refresh' }}
        iconCategory="utility"
        iconName="refresh"
        iconVariant="border-filled"
        variant="icon"
        onClick={onRefresh}
        responsive
      />
    </PageHeaderControl>
  );
}

const ReportView = () => {
  const isMobile = useContext(MobileContext);
  const [selectedSemester, setSelectedSemester] = useState({ id: 'gesamt', label: 'Gesamt' });
  const [report, loadReport, spinner] = useReport(selectedSemester);

  const handleSemesterSelect = selectedItem => {
    setSelectedSemester(selectedItem);
  };

  return (
    <>
      {spinner}

      <PageHeader
        icon={<Icon category="standard" name="report" />}
        label="Auswertungen"
        title={selectedSemester.label}
        truncate
        variant="object-home"
        className="slds-var-m-bottom_small"
        onRenderActions={() => HeaderControls(loadReport)}
        nameSwitcherDropdown={<NameSwitcherDropdown onSelect={handleSemesterSelect} />}
      />

      <div className="slds-grid">
        {!isMobile && (
          <div className="slds-col slds-size_4-of-12 slds-var-p-right_small">
            <SemesterSelection selectedSemester={selectedSemester} onSelect={handleSemesterSelect} />
          </div>
        )}

        {Boolean(report?.evenings?.length) && (
          <div className="slds-col slds-size_1-of-1 slds-large-size_8-of-12">
            <ReportDetails report={report} />
          </div>
        )}
      </div>
    </>
  );
};

export default ReportView;
