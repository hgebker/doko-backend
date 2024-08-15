import { useContext } from 'react';
import { MobileContext } from 'app';

import DataTable from '@salesforce/design-system-react/components/data-table';
import DataTableRowActions from '@salesforce/design-system-react/components/data-table/row-actions';

import { makeStyles } from '@material-ui/core/styles';
import classNames from '../../../node_modules/classnames/index';

const useStyles = makeStyles({
  datatable: {
    '& td': {
      overflow: 'visible',
      width: 'unset !important'
    }
  },
  container: ({ maxHeight }) => ({
    maxHeight: maxHeight ?? '50vh',
    overflowY: 'auto'
  })
});

export default function BaseTable({
  items,
  columns,
  rowActions,
  onRowAction,
  enableSelection,
  selectedItem,
  onSelectItem,
  maxHeight
}) {
  const classes = useStyles({ maxHeight });
  const isMobile = useContext(MobileContext);

  const handleItemSelect = (_, data) => {
    const [selectedItem] = data.selection;
    onSelectItem(selectedItem);
  };

  return (
    <div className={classNames('slds-box slds-p-around_none slds-theme_default', classes.container)}>
      <DataTable
        items={items}
        stackedHorizontal
        style={{ border: 'none' }}
        striped={isMobile}
        selection={[selectedItem]}
        onRowChange={handleItemSelect}
        selectRows={enableSelection && 'radio'}
        className={classes.datatable}>
        {columns?.length && columns}

        {rowActions?.length && (
          <DataTableRowActions options={rowActions} onAction={onRowAction} menuPosition="overflowBoundaryElement" />
        )}
      </DataTable>
    </div>
  );
}
