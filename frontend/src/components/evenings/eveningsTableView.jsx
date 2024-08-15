import PageHeader from '@salesforce/design-system-react/components/page-header';
import PageHeaderControl from '@salesforce/design-system-react/components/page-header/control';
import Icon from '@salesforce/design-system-react/components/icon';
import Button from '@salesforce/design-system-react/components/button';

import EveningsTable from './eveningsTable';
import ChangeViewDropdown from '../base/changeViewDropdown';
import NameSwitcherDropdown from '../base/nameSwitcherDropdown';
import SemesterSelection from '../base/semesterSelection';
import EveningSummary from './eveningSummary';
import { useContext } from 'react';
import { MobileContext } from 'app';

function HeaderActions(onNewClicked) {
  return (
    <PageHeaderControl>
      <Button label="Neu" onClick={() => onNewClicked()} responsive />
    </PageHeaderControl>
  );
}

function HeaderControls(onRefresh, selectedView, onViewChange) {
  return (
    <>
      <PageHeaderControl>
        <ChangeViewDropdown selectedView={selectedView} onSelect={onViewChange} />
      </PageHeaderControl>
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
    </>
  );
}

export default function EveningsTableView({
  evenings,
  onOpenModal,
  onRefresh,
  selectedSemester,
  onSemesterSelected,
  selectedView,
  onViewChange,
  onDelete,
  selectedEvening,
  onEveningSelected
}) {
  const isMobile = useContext(MobileContext);

  return (
    <>
      <PageHeader
        icon={<Icon category="standard" name="education" />}
        label="Reg. Einnahmen"
        title={selectedSemester.label}
        truncate
        variant="object-home"
        info={`${evenings.length} Ergebnisse`}
        onRenderActions={() => HeaderActions(onOpenModal)}
        onRenderControls={() => HeaderControls(onRefresh, selectedView, onViewChange)}
        nameSwitcherDropdown={<NameSwitcherDropdown onSelect={onSemesterSelected} />}
        className="slds-var-m-bottom_small"
      />

      <div className="slds-grid">
        {!isMobile && (
          <div className="slds-col slds-size_4-of-12 slds-var-p-right_small">
            <SemesterSelection selectedSemester={selectedSemester} onSelect={onSemesterSelected} />
          </div>
        )}

        <div className="slds-col slds-size_1-of-1 slds-large-size_8-of-12">
          <div className="slds-var-m-bottom_small">
            <EveningsTable
              evenings={evenings}
              onUpdate={onOpenModal}
              onDelete={onDelete}
              selectedEvening={selectedEvening}
              onEveningSelected={onEveningSelected}
            />
          </div>

          <div className="slds-card__footer slds-box slds-box_small slds-theme_default">
            <EveningSummary selectedEvening={selectedEvening} />
          </div>
        </div>
      </div>
    </>
  );
}
