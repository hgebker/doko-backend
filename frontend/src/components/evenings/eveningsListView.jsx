import { useContext, useState } from 'react';

import EveningList from './eveningList';
import EveningDetailCard from './eveningDetailCard';

import SplitView from '@salesforce/design-system-react/components/split-view';
import { MobileContext } from 'app';
import classNames from 'classnames';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  '@media screen and (min-width: 500px)': {
    container: {
      maxHeight: '90vh'
    }
  }
});

export default function EveningsListView({
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
  const [viewOpen, setViewOpen] = useState(true);

  const isMobile = useContext(MobileContext);
  const classes = useStyles();

  const handleEveningSelected = selectedEvening => {
    onEveningSelected(selectedEvening);

    if (isMobile) {
      setViewOpen(false);
    }
  };

  return (
    <SplitView
      className={classNames('slds-theme_default slds-box_border', classes.container)}
      isOpen={viewOpen}
      master={
        <EveningList
          evenings={evenings}
          selectedEvening={selectedEvening}
          onEveningSelected={handleEveningSelected}
          selectedSemester={selectedSemester}
          onSemesterSelected={onSemesterSelected}
          onNewClicked={onOpenModal}
          onRefresh={onRefresh}
          selectedView={selectedView}
          onViewChange={onViewChange}
        />
      }
      detail={<EveningDetailCard selectedEvening={selectedEvening} onEdit={onOpenModal} onDelete={onDelete} />}
      events={{
        onClose: () => setViewOpen(false),
        onOpen: () => setViewOpen(true)
      }}
    />
  );
}
