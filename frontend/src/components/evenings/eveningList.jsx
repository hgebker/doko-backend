import { useState } from 'react';
import SplitViewHeader from '@salesforce/design-system-react/components/split-view/header';
import SplitViewListbox from '@salesforce/design-system-react/components/split-view/listbox';
import Button from '@salesforce/design-system-react/components/button';
import PageHeaderControl from '@salesforce/design-system-react/components/page-header/control';
import Icon from '@salesforce/design-system-react/components/icon';

import { sortUtils } from 'services/utils';

import { SEMESTER_LABEL } from 'constants/semester';
import { formatNumber } from 'services/utils/baseUtils';
import ChangeViewDropdown from 'components/base/changeViewDropdown';
import NameSwitcherDropdown from 'components/base/nameSwitcherDropdown';

const HeaderActions = onNewClicked => {
  return (
    <PageHeaderControl>
      <Button label="Neu" onClick={() => onNewClicked()} responsive />
    </PageHeaderControl>
  );
};

const HeaderControls = (onRefresh, selectedView, onViewChange) => (
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

const EveningList = ({
  evenings,
  selectedEvening,
  onEveningSelected,
  selectedSemester,
  onSemesterSelected,
  onNewClicked,
  onRefresh,
  selectedView,
  onViewChange
}) => {
  const [sortDirection, setSortDirection] = useState(sortUtils.SORT_OPTIONS.UP);

  const sortedList = sortUtils.sortObjectArray(evenings, 'date', sortDirection);
  const options = sortedList.map(evening => ({
    id: evening.date,
    label: evening.date,
    bottomLeftText: SEMESTER_LABEL[evening.semester],
    topRightText: 'Gesamt:',
    bottomRightText: formatNumber(evening.sum)
  }));
  const selectedOption = options.find(option => option.id === selectedEvening?.date);

  const handleEveningSelected = selectedEvening => {
    onEveningSelected(evenings.find(({ date }) => date === selectedEvening.id));
  };

  const handleSort = () => {
    const { DOWN, UP } = sortUtils.SORT_OPTIONS;
    setSortDirection(sortDirection => (sortDirection === DOWN ? UP : DOWN));
  };

  return (
    <>
      <SplitViewHeader
        key="1"
        title={selectedSemester.label}
        label="Reg. Einnahmen"
        truncate
        variant="object-home"
        icon={<Icon assistiveText={{ label: 'Abende' }} category="standard" name="education" />}
        info={`${options.length} Ergebnisse`}
        onRenderActions={() => HeaderActions(onNewClicked)}
        onRenderControls={() => HeaderControls(onRefresh, selectedView, onViewChange)}
        nameSwitcherDropdown={<NameSwitcherDropdown onSelect={onSemesterSelected} />}
      />

      <SplitViewListbox
        key="2"
        labels={{ header: 'date' }}
        options={options}
        events={{
          onSelect: (_, { item }) => handleEveningSelected(item),
          onSort: handleSort
        }}
        sortDirection={sortDirection}
        selection={[selectedOption]}
        className="capitalize"
      />
    </>
  );
};

export default EveningList;
